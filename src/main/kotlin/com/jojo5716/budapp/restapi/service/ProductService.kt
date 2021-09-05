package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.dao.ProductDAO
import com.jojo5716.budapp.restapi.domain.Product
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(val productDAO: ProductDAO) : BasicCRUD<Product, Int> {

    override fun findAll(): List<Product> = this.productDAO.findAll()

    override fun findById(id: Int): Product? = this.productDAO.findByIdOrNull(id)

    override fun save(t: Product): Product {
        if (this.existByName(t.name)) {
            throw DuplicateKeyException("${t.name} does exist")
        }
        return this.productDAO.save(t)
    }

    override fun update(t: Product): Product {
        if (this.productDAO.existsById(t.id)) {
            return this.productDAO.save(t)
        }

        throw EntityNotFoundException("${t.name} does exist")
    }

    override fun deleteById(id: Int): Product {
        return this.findById(id)?.apply {
            this@ProductService.productDAO.deleteById(id)
        } ?: throw EntityNotFoundException("${id} does not exist")
    }

    override fun existByName(name: String): Boolean {
        println("Exist!!!! jajaja")

        return true
       //  return this.productDAO.existByName(name) || throw EntityNotFoundException("$name does not exist")
    }
}