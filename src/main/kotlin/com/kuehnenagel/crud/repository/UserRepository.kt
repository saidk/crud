package com.kuehnenagel.crud.repository

import com.kuehnenagel.crud.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>
