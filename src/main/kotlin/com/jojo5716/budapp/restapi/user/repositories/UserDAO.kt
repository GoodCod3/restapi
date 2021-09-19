package com.jojo5716.budapp.restapi.user.repositories

import com.jojo5716.budapp.restapi.user.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDAO : JpaRepository<User, Int> {
    fun existsByEmail(email: String): Boolean
    // fun findByEmailAndName(email: String): User
}