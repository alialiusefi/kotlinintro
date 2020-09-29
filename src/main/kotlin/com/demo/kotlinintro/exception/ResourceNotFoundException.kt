package com.demo.kotlinintro.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND) // not needed it exception handler is implemented
class ResourceNotFoundException(message: String = "The resource that you requested was not found") : Exception(message) // specify the resource name