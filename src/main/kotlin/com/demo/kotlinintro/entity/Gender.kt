package com.demo.kotlinintro.entity

enum class Gender(var order : Int) {
    MALE(0), FEMALE(1);

    fun switchOrder() {
        MALE.order = 1
        FEMALE.order = 0
    }
}