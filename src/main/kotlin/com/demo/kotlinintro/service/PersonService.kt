package com.demo.kotlinintro.service

import com.demo.kotlinintro.dto.PersonDTO
import com.demo.kotlinintro.entity.Person

interface PersonService {

    fun getAllPersons(): List<Person>

    fun getAllAdultPersons(): List<Person>

    fun addPerson(person: PersonDTO): Person
}