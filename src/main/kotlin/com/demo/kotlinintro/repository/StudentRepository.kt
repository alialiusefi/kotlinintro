package com.demo.kotlinintro.repository

import com.demo.kotlinintro.entity.Student
import com.mongodb.client.result.DeleteResult

interface StudentRepository {

    fun findAll() : List<Student>

    fun findById(uuid : String) : Student?

    fun save(student : Student) : Student

    fun delete(student : Student) : DeleteResult
}
