package com.demo.kotlinintro.controller

import com.demo.kotlinintro.converter.toStudent
import com.demo.kotlinintro.converter.toStudentDTO
import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.handler.ApiError
import com.demo.kotlinintro.handler.ConstraintError
import com.demo.kotlinintro.service.StudentService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/students")
@Validated
class StudentController(private val studentService: StudentService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all students")
    @ApiResponse(code = 200, message = "Success")
    fun getAllStudents(): List<StudentDTO> = studentService.getAllStudents().map { it.toStudentDTO() }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find student by UUID")
    @ApiResponses(
            ApiResponse(code = 200, message = "Found"),
            ApiResponse(code = 404, message = "Student Not Found", response = ApiError::class),
            ApiResponse(code = 400, message = "Incorrect UUID", response = ApiError::class)
    )
    fun getStudent(@ApiParam @PathVariable @Valid id: UUID):
            StudentDTO = studentService.getStudent(id).toStudentDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add student")
    @ApiResponses(
            ApiResponse(code = 201, message = "Created"),
            ApiResponse(code = 400, message = "Invalid body", response = ConstraintError::class),
            ApiResponse(code = 409, message = "Duplicate student", response = ApiError::class)
    )
    fun addStudent(@Valid @RequestBody studentDTO: StudentDTO): StudentDTO = studentService.addStudent(studentDTO.toStudent()).toStudentDTO()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Edit student")
    @ApiResponses(
            ApiResponse(code = 200, message = "Edited"),
            ApiResponse(code = 400, message = "Invalid body", response = ConstraintError::class),
            ApiResponse(code = 404, message = "Student Not Found", response = ApiError::class)
            )
    fun editStudent(@PathVariable @Valid id: UUID, @Valid @RequestBody studentDTO:
    StudentDTO): StudentDTO = studentService.editStudent(id, studentDTO.toStudent()).toStudentDTO()

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete student")
    @ApiResponses(
            ApiResponse(code = 204, message = "Deleted"),
            ApiResponse(code = 404, message = "Student Not Found", response = ApiError::class)
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@PathVariable @Valid id: UUID) = studentService.deleteStudent(id)

}
