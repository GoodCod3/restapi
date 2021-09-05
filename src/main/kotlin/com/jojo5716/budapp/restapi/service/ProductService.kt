package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.dao.ProductDAO
import com.jojo5716.budapp.restapi.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService : BasicCRUD<Product, String> {

    @Autowired
    private lateinit var providerDAO: ProductDAO

    override fun findAll(): List<Product> = this.providerDAO.findAll()

    override fun findById(id: String): Product? = this.providerDAO.findByIdOrNull(id)

    override fun save(t: Product): Product {
        if (this.providerDAO.existsById(t.name)) {
            throw DuplicateKeyException("${t.name} does exist")
        }
        return this.providerDAO.save(t)
    }

    override fun update(t: Product): Product {
        if (this.providerDAO.existsById(t.name)) {
            return this.providerDAO.save(t)
        }

        throw EntityNotFoundException("${t.name} does exist")
    }

    override fun deleteById(id: String): Product {
        return this.findById(id)?.apply {
            this@ProductService.providerDAO.deleteById(id)
        } ?: throw EntityNotFoundException("${id} does not exist")
    }
}