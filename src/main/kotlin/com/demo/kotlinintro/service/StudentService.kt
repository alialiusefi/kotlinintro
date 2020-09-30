package com.demo.kotlinintro.service

import com.demo.kotlinintro.entity.Student
import java.util.*

interface StudentService {

    fun getStudent(uuid: UUID): Student

    fun getAllStudents(): List<Student>

    fun addStudent(student: Student): Student

    fun editStudent(uuid: UUID, givenStudent: Student): Student

    fun deleteStudent(uuid: UUID)

}
