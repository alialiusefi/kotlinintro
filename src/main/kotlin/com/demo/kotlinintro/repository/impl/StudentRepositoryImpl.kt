package com.demo.kotlinintro.repository.impl

import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.repository.StudentRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryImpl(private val mongoTemplate: MongoTemplate) : StudentRepository {

    override fun findAll(): List<Student> = mongoTemplate.findAll()

    override fun findById(uuid: String): Student? = mongoTemplate.findById(uuid, Student::class.java)

    override fun save(student: Student): Student = mongoTemplate.save(student)

    override fun delete(student: Student) = mongoTemplate.remove(student)

    override fun findByStudent(student: Student): Student? {
        val criteria = Criteria.where("fullName").isEqualTo(student.fullName)
                .and("email").isEqualTo(student.email)
                .and("dateOfBirth").isEqualTo(student.dateOfBirth)
                .and("active").isEqualTo(student.active)
                .and("yearEnrolled").isEqualTo(student.yearEnrolled)

        val query = Query.query(criteria)

        return mongoTemplate.findOne(query, Student::class.java)
    }

}
