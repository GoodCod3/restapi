package com.jojo5716.budapp.restapi.dao

import com.jojo5716.budapp.restapi.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDAO: JpaRepository<Product, String>