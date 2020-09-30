package com.demo.kotlinintro.handler

import com.demo.kotlinintro.exception.ResourceNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest):
            ResponseEntity<Any>{
        val result = ex.bindingResult

        val messages = result.fieldErrors.map { fieldError: FieldError ->
            "object: ${fieldError.objectName}, field: ${fieldError.field}, message: ${fieldError.defaultMessage}"
        }
                .toList()

        val url = getPath(request)

        val body = ApiError(messages = messages, status = HttpStatus.BAD_REQUEST.value(), path = url)

        return ResponseEntity
                .badRequest()
                .body(body)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleJavaxConstraintViolationException(
            ex: ConstraintViolationException, request: WebRequest): ResponseEntity<ApiError> {
        val url = getPath(request)

        val messages = ex.constraintViolations
                .map { obj: ConstraintViolation<*> -> obj.message }
                .toList()

        val body = ApiError(messages = messages, status = HttpStatus.BAD_REQUEST.value(), path = url)

        return ResponseEntity
                .badRequest()
                .body(body)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
            ex: MethodArgumentTypeMismatchException, request: WebRequest): ResponseEntity<ApiError> {
        val error = "${ex.name} should be of type ${ex.requiredType?.name}"

        val url: String = getPath(request)

        val apiError = ApiError(messages = listOf(error), status = HttpStatus.BAD_REQUEST.value(), path = url)

        return ResponseEntity(
                apiError, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
            ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<ApiError> {
        val url = getPath(request)

        val apiError = ApiError(
                messages = listOf(requireNotNull(ex.message)),
                status = HttpStatus.NOT_FOUND.value(),
                path = url)

        return ResponseEntity(
                apiError, HttpHeaders(), HttpStatus.NOT_FOUND)
    }

    private fun getPath(webRequest: WebRequest) = (webRequest as ServletWebRequest).request.requestURI.toString()

}
