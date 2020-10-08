package com.demo.kotlinintro.handler

import java.time.LocalDateTime

data class ApiError(val messages : List<String>,
                    val timestamp : LocalDateTime = LocalDateTime.now(),
                    val status : Int,
                    val path : String)