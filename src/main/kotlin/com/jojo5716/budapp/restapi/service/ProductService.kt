package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.dao.ProductDAO
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.utils.update
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.context.WebApplicationContext

@Service
class ProductService : BasicCRUD<Product, String> {

    @Autowired
    private lateinit var productDao: ProductDAO

    override fun findAll(): List<Product> = this.productDao.findAll()

    override fun findById(id: String): Product? = productDao.findByIdOrNull(id)

    override fun save(t: Product): Boolean = this.productDao.save(t).let {
        return true
    }

    override fun update(t: Product): Boolean = this.productDao.save(t).let {
        return true
    }

    override fun deleteById(id: String): Boolean = this.productDao.deleteById(id).let {
        return true
    }
}