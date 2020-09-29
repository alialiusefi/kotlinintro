package com.demo.kotlinintro.repository.impl

import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.exception.ResourceNotFoundException
import com.demo.kotlinintro.repository.StudentRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(val mongoTemplate: MongoTemplate) : StudentRepository {

    override fun findAll(): List<Student> = mongoTemplate.findAll()

    override fun findById(id: String): Student? = mongoTemplate.findById(id, Student::class.java) ?: throw ResourceNotFoundException("Can't find the" +
            " resource with id: $id") // throw an exception is the service layer responsibility

    override fun save(student: Student): Student { // expression body
        return mongoTemplate.save(student)
    }

    override fun delete(student: Student) = mongoTemplate.remove(student)

}