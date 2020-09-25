package com.demo.kotlinintro.repository

import com.demo.kotlinintro.entity.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : MongoRepository<Student, String>