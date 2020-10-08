package com.demo.kotlinintro.converter

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student

fun Student.toStudentDTO(): StudentDTO = StudentDTO(id = this.id,
            fullName = this.fullName,
            yearEnrolled = this.yearEnrolled,
            email = this.email,
            active = this.active,
            dateOfBirth = this.dateOfBirth)


fun StudentDTO.toStudent(): Student =
        Student(email = this.email,
                yearEnrolled = this.yearEnrolled,
                dateOfBirth = this.dateOfBirth,
                active = this.active,
                fullName = this.fullName)