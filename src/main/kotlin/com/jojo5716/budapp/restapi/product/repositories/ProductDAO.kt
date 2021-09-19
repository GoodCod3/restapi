package com.jojo5716.budapp.restapi.product.repositories

import com.jojo5716.budapp.restapi.product.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDAO : JpaRepository<Product, Int>