package com.kuehnenagel.crud

import com.kuehnenagel.crud.dto.ApiUser
import com.kuehnenagel.crud.model.User
import com.kuehnenagel.crud.repository.UserRepository
import com.kuehnenagel.crud.service.NotFoundException
import com.kuehnenagel.crud.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.Optional


@ExtendWith(MockitoExtension::class)
class UserServiceTests {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var restTemplate: RestTemplate

    @Test
    fun getAllUsers() {
        val userService = UserService(userRepository, restTemplate)
        val user = UserFixture.user
        `when`(userRepository.findAll()).thenReturn(mutableListOf(user))

        val result = userService.getAllUsers()
        assertEquals(result.size, 1)
        assertEquals(result.first().age, user.age)
        assertEquals(result.first().email, user.email)
        assertEquals(result.first().name, user.name)
        assertEquals(result.first().phoneNumber, user.phoneNumber)
    }

    @Test
    fun `getUser - throws exception if not found`() {
        val userService = UserService(userRepository, restTemplate)
        `when`(userRepository.findById(1)).thenReturn(Optional.empty())

        assertThrows<NotFoundException> { userService.getUser(1) }
    }

    @Test
    fun getUser() {
        val userService = UserService(userRepository, restTemplate)
        val user = UserFixture.user
        `when`(userRepository.findById(1)).thenReturn(Optional.of(user))

        val result = userService.getUser(1)
        assertEquals(result.age, user.age)
        assertEquals(result.email, user.email)
        assertEquals(result.name, user.name)
        assertEquals(result.phoneNumber, user.phoneNumber)
    }

    @Test
    fun createOrUpdateUser() {
        val userService = UserService(userRepository, restTemplate)
        val user = UserFixture.user
        val apiUser = UserFixture.apiUser
        val json = UserFixture.json
        `when`(restTemplate.getForObject<String?>("https://api.agify.io/?name=${apiUser.name}")).thenReturn(json)
        `when`(userRepository.save(user)).thenReturn(user)

        val result = userService.createOrUpdateUser(apiUser)
        assertEquals(result.age, user.age)
        assertEquals(result.email, user.email)
        assertEquals(result.name, user.name)
        assertEquals(result.phoneNumber, user.phoneNumber)
    }

    @Test
    fun `deleteUser - throws exception`() {
        val userService = UserService(userRepository, restTemplate)
        `when`(userRepository.findById(1)).thenReturn(Optional.empty())

        assertThrows<NotFoundException> { userService.deleteUser(1) }
    }

    @Test
    fun deleteUser() {
        val userService = UserService(userRepository, restTemplate)
        val user = UserFixture.user
        `when`(userRepository.findById(1)).thenReturn(Optional.of(user))

        userService.deleteUser(1)
        verify(userRepository).delete(user)
    }
}

object UserFixture {
    val user = User(name = "name", email = "email", phoneNumber = "4321", age = 30)

    val apiUser = ApiUser(name = "name", email = "email", phoneNumber = "4321")

    val json = """
        {"name":"name","age":30,"count":46664}
    """.trimIndent()
}
