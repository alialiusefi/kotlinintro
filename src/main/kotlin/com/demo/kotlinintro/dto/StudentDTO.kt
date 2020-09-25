package com.demo.kotlinintro.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class StudentDTO(@JsonProperty("id", access = JsonProperty.Access.READ_ONLY) val id : String?,
                      val fullName : String,
                      val yearEnrolled : Int,
                      val dateOfBirth : LocalDate,
                      val email : String,
                      val active : Boolean = false)