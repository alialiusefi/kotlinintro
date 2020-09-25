package com.demo.kotlinintro.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate
import java.util.*

@Document("students")
data class Student(@MongoId val id : String = UUID.randomUUID().toString(), val fullName : String, val yearEnrolled : Int, val dateOfBirth : LocalDate,
              var email : String, var active : Boolean = false)
