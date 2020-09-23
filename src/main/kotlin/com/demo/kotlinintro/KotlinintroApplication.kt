package com.demo.kotlinintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class KotlinintroApplication

fun main(args: Array<String>) {
	runApplication<KotlinintroApplication>(*args)
}