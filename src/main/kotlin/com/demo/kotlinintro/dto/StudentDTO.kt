package com.demo.kotlinintro.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.Past
import javax.validation.constraints.Positive

// the same about constants
const val EMAIL_REGEX: String = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"

data class StudentDTO(@JsonProperty("id", access = JsonProperty.Access.READ_ONLY) val id: String?,
                      @field:Length(min = 3, max = 50) val fullName: String,
                      @field:Positive
                      @field:Min(1970) val yearEnrolled: Int,
                      @field:Past val dateOfBirth: LocalDate,
                      @field:Email(regexp = EMAIL_REGEX) val email: String,
                      val active: Boolean = false)
