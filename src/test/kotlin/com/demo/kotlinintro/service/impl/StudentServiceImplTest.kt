package com.demo.kotlinintro.service.impl

import com.demo.kotlinintro.entity.Student
import com.demo.kotlinintro.repository.StudentRepository
import com.demo.kotlinintro.service.StudentService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class StudentServiceImplTest {

    @MockK
    lateinit var studentRepository: StudentRepository

    @InjectMockKs
    lateinit var studentService: StudentService

    @Test
    fun getStudent() {
        val uuid = "51876e46-8c15-47be-93b8-d3954a849ad7"
        val expected = Student(id = uuid,
                fullName = "Ali Al-Iusefi",
                yearEnrolled = 2017,
                dateOfBirth = LocalDate.of(2000,1,1),
        email = "email@example.com",
        active = false)

        every { studentRepository.findById(uuid) } returns expected
        val actual : Student = studentService.getStudent(uuid)
        assertEquals(expected,actual)
    }

    @Test
    fun getAllStudents() {
    }

    @Test
    fun addStudent() {
    }

    @Test
    fun editStudent() {
    }

    @Test
    fun deleteStudent() {
    }
}