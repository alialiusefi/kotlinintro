package com.demo.kotlinintro.handler

import com.demo.kotlinintro.exception.ResourceNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException


@ControllerAdvice
public class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val result = ex.bindingResult

        val apiErrors = result.fieldErrors.stream()
                .map<Any> { fieldError: FieldError ->
                    ApiError("object: ${fieldError.objectName}, field: ${fieldError.field}, message: ${fieldError.defaultMessage}")
                }
                .collect(Collectors.toList())

        return ResponseEntity
                .badRequest()
                .body(apiErrors)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleJavaxConstraintViolationException(
            ex: ConstraintViolationException): ResponseEntity<Any?>? {
        val apiErrors = ex.constraintViolations.stream()
                .map { obj: ConstraintViolation<*> -> obj.message }
                .map<Any> { ApiError(it) }
                .collect(Collectors.toList())
        return ResponseEntity
                .badRequest()
                .body(apiErrors)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
            ex: MethodArgumentTypeMismatchException, request: WebRequest): ResponseEntity<Any> {
        val error = "${ex.name} should be of type ${ex.requiredType?.name}"
        val apiError = ApiError(error)
        return ResponseEntity(
                apiError, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
            ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(ex.message)
        return ResponseEntity(
                apiError, HttpHeaders(), HttpStatus.NOT_FOUND)
    }
}