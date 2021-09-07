package com.jojo5716.budapp.restapi.dao

import com.jojo5716.budapp.restapi.domain.DispensarySetting
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductDAO : JpaRepository<Product, String>

@Repository
interface ProviderDAO : JpaRepository<Provider, Int>

@Repository
interface UserDAO : JpaRepository<User, Int> {
    fun existsByEmail(email: String): Boolean
    // fun findByEmailAndName(email: String): User
}

@Repository
interface DispensarySettingDAO : JpaRepository<DispensarySetting, Int>