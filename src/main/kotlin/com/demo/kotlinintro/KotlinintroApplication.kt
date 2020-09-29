package com.demo.kotlinintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext // remove unused imports: ctrl + alt + o

@SpringBootApplication
class KotlinintroApplication // Camel case

fun main(args: Array<String>) {
	runApplication<KotlinintroApplication>(*args)
}