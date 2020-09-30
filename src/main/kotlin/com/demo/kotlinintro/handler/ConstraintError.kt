package com.demo.kotlinintro.handler

import java.time.LocalDateTime

data class ConstraintError(val fieldMessages : List<FieldMessage>,
                           val timestamp : LocalDateTime = LocalDateTime.now(),
                           val status : Int,
                           val path : String) {

    class FieldMessage(val field: String, val message: String)
}