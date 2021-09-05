package com.jojo5716.budapp.restapi

import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.service.ProductService
import com.jojo5716.budapp.restapi.service.ProviderService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(
    private val productService: ProductService,
    private val providerService: ProviderService
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val defaultProvicer = Provider(id = 1, name = "Default provider", email = "default@provider.com")
        if (!providerService.providerDAO.existsById(defaultProvicer.id)) {
            providerService.save(defaultProvicer)
        }
        listOf(
            Product(name = "Product 1", price = 0.0, stock = 1, provider = defaultProvicer),
            Product(name = "Product 2", price = 0.0, stock = 1, provider = defaultProvicer)
        ).forEach {
            if (!productService.productDAO.existsById(it.name)) {
                println("Saving ${it.name}")

                productService.save(it)
            }
        }
    }
}