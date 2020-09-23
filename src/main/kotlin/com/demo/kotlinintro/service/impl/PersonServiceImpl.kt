package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.dto.PersonDTO
import com.demo.kotlinintro.repository.PersonRepository
import com.demo.kotlinintro.service.PersonService
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(val personRepository: PersonRepository) : PersonService {

    override fun getAllPersons(): List<PersonDTO> {
        return personRepository.findAll()
    }

    override fun getAllAdultPersons(): List<PersonDTO> {
        val listOfPeople : MutableList<PersonDTO> = personRepository.findAll()
        return listOfPeople.filter { if (it.age != null) it.age < 18 else false}
    }

    override fun addPerson(person: PersonDTO) = personRepository.save(person)

    // extension function conversion DTO -> Entity and vice versa
}