package com.demo.kotlinintro.entity

import com.demo.kotlinintro.dto.StudentDTO

fun Student.toStudentDTO(): StudentDTO {
    return StudentDTO(id = this.id,
            fullName = this.fullName,
            yearEnrolled = this.yearEnrolled,
            email = this.email,
            active = this.active,
            dateOfBirth = this.dateOfBirth)
}
