package com.demo.kotlinintro.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "person")
data class Person(var id : String?, var name : String, var age : Int = 0, val listOfNotes : List<String> = arrayListOf(), var gender : Gender?)
