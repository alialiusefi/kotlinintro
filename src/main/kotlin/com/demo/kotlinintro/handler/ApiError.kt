package com.demo.kotlinintro.handler

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(val message : String,
                    val timestamp : LocalDateTime = LocalDateTime.now(),
                    val status : HttpStatus,
                    val path : String)
