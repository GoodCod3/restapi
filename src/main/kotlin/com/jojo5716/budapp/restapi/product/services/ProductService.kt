package com.jojo5716.budapp.restapi.product.services

import com.jojo5716.budapp.restapi.product.entities.Product
import com.jojo5716.budapp.restapi.product.repositories.ProductDAO
import com.jojo5716.budapp.restapi.product.requests.ProductBuyRequestParams
import com.jojo5716.budapp.restapi.services.BasicCRUD
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(val productDAO: ProductDAO) : BasicCRUD<Product, Int> {
    override fun findAll(): List<Product> = this.productDAO.findAll()

    override fun findById(id: Int): Product? = this.productDAO.findByIdOrNull(id)

    override fun save(t: Product): Product {
        if (this.productDAO.existsById(t.id)) {
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
        } ?: throw EntityNotFoundException("$id does not exist")
    }

    fun buy(body: ProductBuyRequestParams): Product {
        val product = this.findById(body.id)
        if (product != null && product.stock >= body.stock) {
            product.stock -= body.stock

            return this.update(product)
        } else {
            throw EntityNotFoundException("${body.id} does not exist or out of stock")
        }
    }
}