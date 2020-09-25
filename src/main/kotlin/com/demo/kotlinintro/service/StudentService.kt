package com.demo.kotlinintro.service

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student

interface StudentService {

    fun getStudent(id : String) : Student

    fun getAllStudents() : List<Student>

    fun addStudent(student: StudentDTO) : Student

    fun editStudent(id : String, givenStudent: StudentDTO) : Student

    fun deleteStudent(id : String)

}