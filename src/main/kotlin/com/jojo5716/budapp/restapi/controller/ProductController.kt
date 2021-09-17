package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.DispensaryGeneticProductProfile
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.service.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Size


data class ProductBuyRequestParams(
    @get:Min(1)
    var id: String = "",
    @get:Min(0)
    var stock: Int,
)

data class ProductCreateRequestParams(
    @get:Size(min = 3, max = 50)
    var name: String,

    @get:Min(0)
    var price: Double = 0.0,

    @get:Min(value = 0)
    var stock: Double = 0.0,

    @ManyToOne
    var provider: Provider,

    var is_genetic: Boolean = false,
)

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService) : BasicController<Product, String>(productService) {
    @ApiOperation("Create a product", notes = "Return a product")
    @PostMapping
    override fun save(@Valid @RequestBody body: Product): ResponseEntity<Product> {
        println("Creating product...")
        println(body)
        val productObject =  Product(
            name = "Product 2",
            price = 0.0,
            stock = 1.0,
            provider = body.provider,
            dispensaryGeneticProductProfile = null
        )
        val product = productService.save(productObject)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }


    @ApiOperation("Buy a product", notes = "Buy a product if has a stock")
    @PostMapping("/buy/{id}")
    fun buy(@RequestBody body: ProductBuyRequestParams): ResponseEntity<Boolean> {
        productService.buy(body)

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}