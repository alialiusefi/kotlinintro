package com.demo.kotlinintro.controller

import com.demo.kotlinintro.dto.PersonDTO
import com.demo.kotlinintro.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPersons(): List<com.demo.kotlinintro.entity.Person> = personService.getAllPersons()

    @GetMapping("/adult")
    @ResponseStatus(HttpStatus.OK)
    fun getAllAdultPersons() : List<com.demo.kotlinintro.entity.Person> = personService.getAllAdultPersons()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPerson(person : PersonDTO) : PersonDTO = personService.addPerson(person)

}