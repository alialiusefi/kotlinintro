package com.demo.kotlinintro.dto

import com.demo.kotlinintro.entity.Gender

data class PersonDTO(val id : String?, val name : String, val age : Int = 0, val listOfNotes : List<String> = arrayListOf(), val gender : Gender?)