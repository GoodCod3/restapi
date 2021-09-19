package com.jojo5716.budapp.restapi.dao

import com.jojo5716.budapp.restapi.domain.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserDAO : JpaRepository<User, Int> {
    fun existsByEmail(email: String): Boolean
    // fun findByEmailAndName(email: String): User
}

@Repository
interface DispensarySettingDAO : JpaRepository<DispensarySetting, Int>

@Repository
interface DispensaryProductGeneticProfileDAO : JpaRepository<DispensaryProductGeneticProfile, Int>
