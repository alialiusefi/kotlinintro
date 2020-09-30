package com.demo.kotlinintro.handler

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(val messages : List<String>,
                    val timestamp : LocalDateTime = LocalDateTime.now(),
                    val status : HttpStatus,
                    val path : String)