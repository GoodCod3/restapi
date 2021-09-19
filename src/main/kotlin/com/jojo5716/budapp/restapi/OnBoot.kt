package com.jojo5716.budapp.restapi

import com.jojo5716.budapp.restapi.dispensary.entities.DispensaryProductGeneticProfile
import com.jojo5716.budapp.restapi.domain.*
import com.jojo5716.budapp.restapi.product.entities.Product
import com.jojo5716.budapp.restapi.product.services.ProductService
import com.jojo5716.budapp.restapi.provider.entities.Provider
import com.jojo5716.budapp.restapi.provider.services.ProviderService
import com.jojo5716.budapp.restapi.service.*
import com.jojo5716.budapp.restapi.user.entities.User
import com.jojo5716.budapp.restapi.user.services.UserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(
    private val productService: ProductService,
    private val providerService: ProviderService,
    private val userService: UserService,
    private val dispensarySettingService: DispensarySettingService,
    private val dispensaryProductGeneticProfileService: DispensaryProductGeneticProfileService
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val defaultProvider: Provider = createProviderIsNeeded()

        createUsersIsNeeded()

        createProductsIfNeeded(defaultProvider)

        createDispensarySettingsIfNeeded()

        createDispensaryProductGeneticProfileIfNeeded()
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
            Product(
                name = "Product 1",
                price = 0.0,
                stock = 1.0,
                provider = provider,
            ),
            Product(
                name = "Product 2",
                price = 0.0,
                stock = 1.0,
                provider = provider,
            )
        ).forEach {
            if (!productService.productDAO.existsById(it.id)) {
                println("Saving ${it.name}")

                productService.save(it)
            }
        }
    }

    fun createDispensarySettingsIfNeeded() {
        listOf(
            DispensarySetting(name = "Dispensary test"),
        ).forEach {
            if (!dispensarySettingService.dispensarySettingDAO.existsById(it.id)) {
                println("Saving ${it.name}")

                dispensarySettingService.save(it)
            }
        }
    }

    fun createDispensaryProductGeneticProfileIfNeeded() {
        productService.findById(1)?.let { it ->
            println(it)
            listOf(
//                DispensaryProductGeneticProfile(product = it, thc = 12.2),
                DispensaryProductGeneticProfile(thc = 12.2),
            ).forEach {
                if (!dispensaryProductGeneticProfileService.dispensaryProductGeneticProfileDAO.existsById(it.id)) {
//                    println("Saving profile for ${it.product.name} - THC: ${it.thc}")
                    println("Saving profile for ${it.id} - THC: ${it.thc}")

                    dispensaryProductGeneticProfileService.save(it)
                } else {
                    println("ELSE")
                    println("\t\t $it")
                }
            }
        }

    }
}