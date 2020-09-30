package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.exception.DuplicateResourceException
import com.demo.kotlinintro.exception.ResourceNotFoundException
import com.demo.kotlinintro.repository.StudentRepository
import com.demo.kotlinintro.service.StudentService
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImpl(private val studentRepository: StudentRepository) : StudentService {

    override fun getStudent(uuid: UUID): Student = studentRepository.findById(uuid.toString())
            ?: throw ResourceNotFoundException("Can't find student with id: $uuid")

    override fun getAllStudents(): List<Student> = studentRepository.findAll()

    override fun addStudent(student: Student): Student {
        if (isExistsStudent(student)) {
            throw DuplicateResourceException("Student with these fields already exists")
        }
        return studentRepository.save(student)
    }

    override fun editStudent(uuid: UUID, givenStudent: Student): Student {
        val oldStudent = getStudent(uuid)
        val newStudent = oldStudent.copy(
                fullName = givenStudent.fullName,
                email = givenStudent.email,
                yearEnrolled = givenStudent.yearEnrolled,
                active = givenStudent.active,
                dateOfBirth = givenStudent.dateOfBirth
        )
        return studentRepository.save(newStudent)
    }

    override fun deleteStudent(uuid: UUID) {
        val student = getStudent(uuid)
        studentRepository.delete(student)
    }

    override fun isExistsStudent(student: Student): Boolean = studentRepository.findByStudent(student) != null

}
