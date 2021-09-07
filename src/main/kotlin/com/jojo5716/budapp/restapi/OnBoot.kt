package com.jojo5716.budapp.restapi

import com.jojo5716.budapp.restapi.domain.DispensarySetting
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.domain.User
import com.jojo5716.budapp.restapi.service.DispensarySettingService
import com.jojo5716.budapp.restapi.service.ProductService
import com.jojo5716.budapp.restapi.service.ProviderService
import com.jojo5716.budapp.restapi.service.UserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping

@Component
class OnBoot(
    private val productService: ProductService,
    private val providerService: ProviderService,
    private val userService: UserService,
    private val dispensarySettingService: DispensarySettingService,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val defaultProvider: Provider = createProviderIsNeeded()

        createUsersIsNeeded()

        createProductsIfNeeded(defaultProvider)

        createDispensarySettingsIfNeeded(defaultProvider)
    }

    fun createProviderIsNeeded(): Provider {
        val defaultProvicer = Provider(id = 1, name = "Default provider", email = "default@provider.com")

        if (!providerService.providerDAO.existsById(defaultProvicer.id)) {
            providerService.save(defaultProvicer)
        }

        return defaultProvicer
    }

    fun createUsersIsNeeded() {
        listOf(
            User(name = "Default user", email = "default@user.com")
        ).forEach {
            if (!userService.userDAO.existsByEmail(it.email)) {
                userService.save(it)
            }
        }
    }

    fun createProductsIfNeeded(provider: Provider) {
        listOf(
            Product(name = "Product 1", price = 0.0, stock = 1, provider = provider),
            Product(name = "Product 2", price = 0.0, stock = 1, provider = provider)
        ).forEach {
            if (!productService.productDAO.existsById(it.name)) {
                println("Saving ${it.name}")

                productService.save(it)
            }
        }
    }

    fun createDispensarySettingsIfNeeded(provider: Provider) {
        listOf(
            DispensarySetting(name = "Dispensary test"),
        ).forEach {
            if (!dispensarySettingService.dispensarySettingDAO.existsById(it.id)) {
                println("Saving ${it.name}")

                dispensarySettingService.save(it)
            }
        }
    }
}