package com.kuehnenagel.crud.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiUser(
    @field:JsonProperty("id") val id: Long? = null,

    @field:JsonProperty("name") val name: String,

    @field:JsonProperty("email") val email: String,

    @field:JsonProperty("age") val age: Int? = null,

    @field:JsonProperty("phoneNumber") val phoneNumber: String,
)
