package com.demo.kotlinintro.repository.impl

import com.demo.kotlinintro.constant.RepositoryConstants
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
        val criteria = Criteria.where(RepositoryConstants.FULLNAME).isEqualTo(student.fullName)
                .and(RepositoryConstants.EMAIL).isEqualTo(student.email)
                .and(RepositoryConstants.DATEOFBIRTH).isEqualTo(student.dateOfBirth)
                .and(RepositoryConstants.ACTIVE).isEqualTo(student.active)
                .and(RepositoryConstants.YEARENROLLED).isEqualTo(student.yearEnrolled)

        val query = Query.query(criteria)

        return mongoTemplate.findOne(query, Student::class.java)
    }

}
