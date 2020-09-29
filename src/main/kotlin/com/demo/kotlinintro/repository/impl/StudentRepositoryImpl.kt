package com.demo.kotlinintro.repository.impl

import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.repository.StudentRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(val mongoTemplate: MongoTemplate) : StudentRepository {

    override fun findAll(): List<Student> = mongoTemplate.findAll()

    override fun findById(id: String): Student? = mongoTemplate.findById(id, Student::class.java)

    override fun save(student: Student): Student = mongoTemplate.save(student)

    override fun delete(student: Student) = mongoTemplate.remove(student)

}