package com.kuehnenagel.crud.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("users")
data class User (
    @Id
    val id: Long? = null,
    val name: String,
    val email: String,
    val age: Int,
    val phoneNumber: String,
    @CreatedDate
    val createdTime: Instant? = null,
    @LastModifiedDate
    val lastUpdated: Instant? = null
)
