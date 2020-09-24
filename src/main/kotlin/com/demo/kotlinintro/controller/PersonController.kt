package com.demo.kotlinintro.controller

import com.demo.kotlinintro.dto.PersonDTO
import com.demo.kotlinintro.entity.Person
import com.demo.kotlinintro.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPersons(): List<Person> = personService.getAllPersons()

    @GetMapping("/adult")
    @ResponseStatus(HttpStatus.OK)
    fun getAllAdultPersons(): List<Person> = personService.getAllAdultPersons()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPerson(@RequestBody person: PersonDTO): PersonDTO = personService.addPerson(person).convertToPersonDTO()

    fun Person.convertToPersonDTO(): PersonDTO {
        return PersonDTO(
                id = this.id,
                age = this.age,
                gender = this.gender,
                name = this.name,
                listOfNotes = this.listOfNotes)
    }
}