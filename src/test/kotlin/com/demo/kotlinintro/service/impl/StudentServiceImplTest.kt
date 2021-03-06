package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.dto.StudentDTO
import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.exception.ResourceNotFoundException
import com.demo.kotlinintro.repository.StudentRepository
import com.mongodb.client.result.DeleteResult
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class StudentServiceImplTest {

    @MockK
    lateinit var studentRepository: StudentRepository

    @InjectMockKs
    lateinit var studentService: StudentServiceImpl

    @Test
    fun shouldReturnStudent() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        val expected = Student(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = false)

        every { studentRepository.findById(uuid) } returns expected

        val actual: Student = studentService.getStudent(uuid)

        assertEquals(expected, actual)
        verify { studentRepository.findById(uuid) }
    }

    @Test
    fun shouldThrowResourceNotFoundExceptionWhenStudentDoesntExist() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        every { studentRepository.findById(uuid) } returns null

        assertThrows<ResourceNotFoundException> { studentService.getStudent(uuid) }
        verify(exactly = 1) { studentRepository.findById(uuid) }
    }

    @Test
    fun shouldReturnAllStudents() {
        val expectedList = listOf(
                Student(id = "51876e46-8c15-47be-93b8-d3954a849ad7",
                        fullName = "Ali Al-Iusefi",
                        yearEnrolled = 2017,
                        dateOfBirth = LocalDate.of(2000, 1, 1),
                        email = "email@example.com",
                        active = false),
                Student(id = "51876e46-8c15-47be-93b8-d3954a849ad8",
                        fullName = "Ali Al-Iusefi2",
                        yearEnrolled = 2019,
                        dateOfBirth = LocalDate.of(2002, 1, 1),
                        email = "email2@example.com",
                        active = true)
        )

        every { studentRepository.findAll() } returns expectedList

        val actualList: List<Student> = studentService.getAllStudents()

        assertEquals(expectedList, actualList)
        verify { studentRepository.findAll() }
    }

    @Test
    fun shouldAddStudentAndReturnNewStudent() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        val studentDTO = StudentDTO(id = null,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = false)

        val expected = Student(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = false)

        every { studentRepository.save(any()) } returns expected

        val actual: Student = studentService.addStudent(studentDTO)

        assertEquals(expected, actual)
        verify { studentRepository.save(any()) }
    }

    @Test
    fun shouldEditStudentAndReturnUpdatedStudent() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        val expected = Student(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = true)

        val editedStudent = StudentDTO(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = true)

        every { studentRepository.findById(uuid) } returns expected
        every { studentRepository.save(any()) } returns expected

        val actual = studentService.editStudent(uuid, editedStudent)

        assertEquals(expected, actual)
        verify { studentRepository.save(any()) }
    }

    @Test
    fun shouldThrowResourceNotFoundExceptionStudentDoesNotExist() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        val studentDTO = StudentDTO(id = null,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = false)

        every { studentRepository.findById(uuid) } returns null

        assertThrows<ResourceNotFoundException> { studentService.editStudent(uuid, studentDTO) }

        verify { studentRepository.findById(uuid) }
        verify(exactly = 0) { studentRepository.save(any()) }
    }

    @Test
    fun shouldDeleteStudent() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        val student = Student(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000, 1, 1),
                email = "email@example.com",
                active = false)

        every { studentRepository.findById(uuid) } returns student

        val acknowledgedDeleteResult = DeleteResult.acknowledged(1L)
        every { studentRepository.delete(student) } returns acknowledgedDeleteResult

        studentService.deleteStudent(uuid)

        verify { studentService.getStudent(uuid) }
        verify { studentRepository.delete(student) }
    }

    @Test
    fun shouldThrowResourceNotFoundExceptionWhenStudentDoesNotExist() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"

        every { studentRepository.findById(uuid) } returns null

        assertThrows<ResourceNotFoundException> { studentService.deleteStudent(uuid) }

        verify { studentRepository.findById(uuid) }
        verify(exactly = 0) { studentRepository.delete(any()) }
    }
}