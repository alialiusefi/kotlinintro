package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.dto.PersonDTO
import com.demo.kotlinintro.entity.Person
import com.demo.kotlinintro.repository.PersonRepository
import com.demo.kotlinintro.service.PersonService
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(val personRepository: PersonRepository) : PersonService {

    override fun getAllPersons(): List<Person> {
        return personRepository.findAll()
    }

    override fun getAllAdultPersons(): List<Person> {
        val listOfPeople: MutableList<Person> = personRepository.findAll()
        return listOfPeople.filter { it.age > 18 }
    }

    override fun addPerson(person: PersonDTO) = personRepository.save(person.convertToPerson())

    fun PersonDTO.convertToPerson(): Person {
        return Person(id = this.id,
                name = this.name ?: "",
                age = this.age ?: 0,
                listOfNotes = this.listOfNotes ?: listOf(),
                gender = this.gender)
    }
}