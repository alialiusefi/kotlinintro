package com.demo.kotlinintro.controller

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.Validator
import javax.validation.constraints.Pattern

const val UUID_REGEX: String = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b"
const val INVALID_UUID_MSG: String = "Invalid UUID format"

@RestController
@RequestMapping("/students")
@Validated
class StudentController(val studentService: StudentService, val validator: Validator) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllStudents(): List<StudentDTO> = studentService.getAllStudents().stream()
            .map { it.toStudentDTO() }
            .collect(Collectors.toList())

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStudent(@PathVariable @Pattern(regexp = UUID_REGEX, message = INVALID_UUID_MSG) id: String):
            StudentDTO = studentService.getStudent(id).toStudentDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addStudent(@Valid @RequestBody studentDTO: StudentDTO): StudentDTO = studentService.addStudent(studentDTO).toStudentDTO()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun editStudent(@PathVariable @Pattern(regexp = UUID_REGEX, message = INVALID_UUID_MSG) id: String, @Valid @RequestBody studentDTO:
    StudentDTO): StudentDTO =
            studentService.editStudent(id, studentDTO)
                    .toStudentDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@PathVariable @Pattern(regexp = UUID_REGEX, message = INVALID_UUID_MSG) id: String) = studentService.deleteStudent(id)

    fun Student.toStudentDTO(): StudentDTO {
        return StudentDTO(id = this.id,
                fullName = this.fullName,
                yearEnrolled = this.yearEnrolled,
                email = this.email,
                active = this.active,
                dateOfBirth = this.dateOfBirth)
    }
}