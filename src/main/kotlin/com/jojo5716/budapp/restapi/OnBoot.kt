package com.jojo5716.budapp.restapi

import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.service.ProductService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(private val productService: ProductService): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        listOf(Product(name="Apple", price=22.2, stock=4), Product(name="Samgsung", price=12.2, stock=40)).forEach{
            println("Saving ${it.name}")

            productService.save(it)
        }
    }
}