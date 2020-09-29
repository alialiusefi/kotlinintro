package com.demo.kotlinintro.controller

import com.demo.kotlinintro.converter.toStudentDTO
import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/students")
@Validated
class StudentController(private val studentService: StudentService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllStudents(): List<StudentDTO> = studentService.getAllStudents().stream()
            .map { it.toStudentDTO() }
            .collect(Collectors.toList())

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStudent(@PathVariable @Valid id: UUID):
            StudentDTO = studentService.getStudent(id).toStudentDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
