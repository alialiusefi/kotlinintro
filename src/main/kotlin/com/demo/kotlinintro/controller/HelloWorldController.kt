package com.demo.kotlinintro.controller

import com.demo.kotlinintro.dto.HelloWorldDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloWorldController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun helloWorld(): HelloWorldDTO {
        return HelloWorldDTO()
    }

}