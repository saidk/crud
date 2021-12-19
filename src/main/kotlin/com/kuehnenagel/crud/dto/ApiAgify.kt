package com.kuehnenagel.crud.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiAgify(
    val name: String,
    val age: Int,
    val count: Long,
)
