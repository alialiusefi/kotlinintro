package com.demo.kotlinintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinIntroApplication

fun main(args: Array<String>) {
    runApplication<KotlinIntroApplication>(*args)
}
