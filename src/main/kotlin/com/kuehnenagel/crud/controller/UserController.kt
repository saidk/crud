package com.kuehnenagel.crud.controller

import com.kuehnenagel.crud.dto.ApiUser
import com.kuehnenagel.crud.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.DeleteMapping
import javax.validation.Valid


@Controller
@CrossOrigin(origins = ["http://localhost:3000"])
class UserController(@Autowired(required = true) val service: UserService) {
    @GetMapping(value = ["/users"], produces = ["application/json"])
    fun getAllUsers(): ResponseEntity<List<ApiUser>>? {
        return ResponseEntity(service.getAllUsers(), HttpStatus.valueOf(200))
    }

    @GetMapping(
        value = ["/users/{userId}"],
        produces = ["application/json"]
    )
    fun getUser(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<ApiUser> {
        return ResponseEntity(service.getUser(userId), HttpStatus.valueOf(200))
    }

    @PostMapping(
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUser(
        @Valid @RequestBody apiUser: ApiUser
    ): ResponseEntity<ApiUser> {
        return ResponseEntity(service.createOrUpdateUser(apiUser), HttpStatus.valueOf(200))
    }

    @PutMapping(
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(
        @Valid @RequestBody apiUser: ApiUser
    ): ResponseEntity<ApiUser> {
        return ResponseEntity(service.createOrUpdateUser(apiUser), HttpStatus.valueOf(200))
    }

    @DeleteMapping(
        value = ["/users/{userId}"]
    )
    fun deleteUser(
        @PathVariable("userId") userId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity(service.deleteUser(userId), HttpStatus.valueOf(204))
    }
}