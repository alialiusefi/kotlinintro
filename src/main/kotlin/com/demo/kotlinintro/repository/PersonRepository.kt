package com.demo.kotlinintro.repository

import com.demo.kotlinintro.entity.Person
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, Long>