package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.exception.ResourceNotFoundException
import com.demo.kotlinintro.repository.StudentRepository
import com.demo.kotlinintro.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(val studentRepository: StudentRepository) : StudentService {

    override fun getStudent(id: String): Student = studentRepository.findById(id) ?: throw ResourceNotFoundException("Can't find resource with id :" +
            " $id")

    override fun getAllStudents(): List<Student> = studentRepository.findAll()

    override fun addStudent(studentDTO: StudentDTO): Student = studentRepository.save(studentDTO.toStudent())

    override fun editStudent(id: String, givenStudentDTO: StudentDTO): Student {
        val oldStudent = getStudent(id)
        val newStudent = oldStudent.copy(
                fullName = givenStudentDTO.fullName,
                email = givenStudentDTO.email,
                yearEnrolled = givenStudentDTO.yearEnrolled,
                active = givenStudentDTO.active,
                dateOfBirth = givenStudentDTO.dateOfBirth
        )
        return studentRepository.save(newStudent)
    }

    override fun deleteStudent(id: String) { studentRepository.delete(getStudent(id)) }

    fun StudentDTO.toStudent(): Student =
            Student(email = this.email,
                    yearEnrolled = this.yearEnrolled,
                    dateOfBirth = this.dateOfBirth,
                    active = this.active,
                    fullName = this.fullName)
}