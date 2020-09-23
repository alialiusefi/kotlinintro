package com.demo.kotlinintro.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "person")
data class Person(val name : String, val age : Int? = 0, val listOfNotes : List<String> = arrayListOf(), val gender : Gender?)
