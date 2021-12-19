package com.kuehnenagel.crud.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kuehnenagel.crud.dto.ApiAgify
import com.kuehnenagel.crud.dto.ApiUser
import com.kuehnenagel.crud.model.User
import com.kuehnenagel.crud.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class UserService(
    private val userRepository: UserRepository,
    private val restTemplate: RestTemplate = RestTemplate()
) {
    val mapper: ObjectMapper = jacksonObjectMapper()

    private fun toApiUser(user: User): ApiUser {
        return ApiUser(
            id = user.id,
            name = user.name,
            email = user.email,
            age = user.age,
            phoneNumber = user.phoneNumber
        )
    }

    private fun toUser(apiUser: ApiUser): User {
        val response = restTemplate.getForObject<String>("https://api.agify.io/?name=${apiUser.name}")
        val apiAgify: ApiAgify = mapper.readValue(response, ApiAgify::class.java)
        return User(
            id = apiUser.id,
            name = apiUser.name,
            email = apiUser.email,
            age = apiAgify.age,
            phoneNumber = apiUser.phoneNumber
        )
    }

    fun getAllUsers(): List<ApiUser> {
        val users = userRepository.findAll()
        return users.map { user ->
            toApiUser(user)
        }
    }

    fun getUser(id: Long): ApiUser {
        val user = userRepository.findById(id).orElseThrow {
            NotFoundException("User with id - $id not found")
        }
        return toApiUser(user)
    }

    fun createOrUpdateUser(apiUser: ApiUser): ApiUser {
        val user = toUser(apiUser)
        val savedUser = userRepository.save(user)
        return toApiUser(savedUser)
    }

    fun deleteUser(id: Long) {
        val user = userRepository.findById(id).orElseThrow {
            NotFoundException("User with id - $id not found")
        }
        userRepository.delete(user)
    }
}

class NotFoundException(message: String) :
    ApiException(description = message, code = "not-found", status = HttpStatus.NOT_FOUND)

open class ApiException(
    val description: String = "",
    val code: String = "default-error",
    val status: HttpStatus = HttpStatus.BAD_REQUEST
) : Exception(description)