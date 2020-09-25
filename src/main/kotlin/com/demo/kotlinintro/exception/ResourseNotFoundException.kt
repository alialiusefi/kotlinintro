package com.demo.kotlinintro.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourseNotFoundException(message: String?) : Exception(message)