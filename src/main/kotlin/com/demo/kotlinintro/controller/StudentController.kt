package com.demo.kotlinintro.controller

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/students")
class StudentController(val studentService: StudentService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllStudents(): List<StudentDTO> = studentService.getAllStudents().stream()
            .map { it.toStudentDTO() }
            .collect(Collectors.toList())

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getStudent(@PathVariable id: String): StudentDTO = studentService.getStudent(id).toStudentDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addStudent(@RequestBody studentDTO: StudentDTO): StudentDTO = studentService.addStudent(studentDTO).toStudentDTO()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun editStudent(@PathVariable id: String, @RequestBody studentDTO: StudentDTO): StudentDTO = studentService.editStudent(id, studentDTO).toStudentDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@PathVariable id: String) = studentService.deleteStudent(id)

    fun Student.toStudentDTO(): StudentDTO {
        return StudentDTO(id = this.id,
                fullName = this.fullName,
                yearEnrolled = this.yearEnrolled,
                email = this.email,
                active = this.active,
                dateOfBirth = this.dateOfBirth)
    }
}