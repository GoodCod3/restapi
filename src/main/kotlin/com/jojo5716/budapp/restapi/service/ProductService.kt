package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.utils.update
import org.springframework.stereotype.Service

@Service
class ProductService : BasicCRUD<Product, String> {
    private val products: MutableSet<Product> = mutableSetOf(Product("Product 1", 22.2), Product("Product 2", 33.1))

    override fun findAll(): List<Product> = products.toList()

    override fun findById(id: String): Product? {
        // return this.products.find { it.name == id }
        return this.products.find { product -> product.name == id }
    }

    override fun save(t: Product): Boolean = this.products.add(t)

    override fun update(t: Product): Boolean = this.products.update(t)

    override fun deleteById(id: String): Boolean = this.products.remove(this.findById(id))
}