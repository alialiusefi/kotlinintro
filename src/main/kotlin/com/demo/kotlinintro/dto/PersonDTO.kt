package com.demo.kotlinintro.dto

import com.demo.kotlinintro.entity.Gender
import com.fasterxml.jackson.annotation.JsonProperty

data class PersonDTO constructor(@JsonProperty("id", access = JsonProperty.Access.READ_ONLY) val id: String?,
                                 val name: String?,
                                 val age: Int?,
                                 val listOfNotes:
                                 List<String>? = arrayListOf(),
                                 val gender: Gender?)
