package com.demo.kotlinintro.service

import com.demo.kotlinintro.dto.PersonDTO

interface PersonService {

    fun getAllPersons(): List<PersonDTO>

    fun getAllAdultPersons(): List<PersonDTO>

    fun addPerson(person: PersonDTO): PersonDTO
}