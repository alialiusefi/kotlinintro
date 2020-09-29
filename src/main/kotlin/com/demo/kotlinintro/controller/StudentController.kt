package com.demo.kotlinintro.controller

import com.demo.kotlinintro.converter.toStudentDTO
import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

// companion object for constants, and in general, controller is not the best place to store validation constants
const val UUID_REGEX: String = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b"
const val INVALID_UUID_MSG: String = "Invalid UUID format"

@RestController
@RequestMapping("/students")
@Validated
class StudentController(private val studentService: StudentService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllStudents(): List<StudentDTO> = studentService.getAllStudents().stream()
            .map { it.toStudentDTO() }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStudent(@PathVariable @Valid id: UUID):
            StudentDTO = studentService.getStudent(id).toStudentDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // could already exist
    fun addStudent(@Valid @RequestBody studentDTO: StudentDTO): StudentDTO = studentService.addStudent(studentDTO).toStudentDTO()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun editStudent(@PathVariable @Valid id: UUID, @Valid @RequestBody studentDTO:
    StudentDTO): StudentDTO =
            studentService.editStudent(id, studentDTO)
                    .toStudentDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@PathVariable @Valid id: UUID) = studentService.deleteStudent(id)

}
