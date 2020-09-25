package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.exception.ResourseNotFoundException
import com.demo.kotlinintro.repository.StudentRepository
import com.demo.kotlinintro.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(val studentRepository: StudentRepository) : StudentService {
    override fun getStudent(id: String): Student = studentRepository.findById(id)
            .orElseThrow { ResourseNotFoundException("Can't find student with id: $id") }


    override fun getAllStudents(): List<Student> = studentRepository.findAll()


    override fun addStudent(student: StudentDTO): Student = studentRepository.save(student.toStudent())


    override fun editStudent(id: String, givenStudent: StudentDTO): Student {
        val oldStudent = getStudent(id)
        val newStudent = oldStudent.copy(
                fullName = givenStudent.fullName,
                email = givenStudent.email,
                yearEnrolled = givenStudent.yearEnrolled,
                active = givenStudent.active,
                dateOfBirth = givenStudent.dateOfBirth
        )
        return studentRepository.save(newStudent)
    }

    override fun deleteStudent(id: String) = studentRepository.delete(getStudent(id))

    fun StudentDTO.toStudent(): Student =
            Student(email = this.email,
                    yearEnrolled = this.yearEnrolled,
                    dateOfBirth = this.dateOfBirth,
                    active = this.active,
                    fullName = this.fullName)
}