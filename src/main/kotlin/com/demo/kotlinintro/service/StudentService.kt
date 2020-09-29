package com.demo.kotlinintro.service

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import java.util.*

interface StudentService {

    fun getStudent(uuid : UUID) : Student

    fun getAllStudents() : List<Student>

    fun addStudent(student: StudentDTO) : Student

    fun editStudent(uuid : UUID, givenStudent: StudentDTO) : Student

    fun deleteStudent(uuid : UUID)

}
