package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.product.entities.Product
import com.jojo5716.budapp.restapi.product.requests.ProductBuyRequestParams
import com.jojo5716.budapp.restapi.product.services.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService) : BasicController<Product, Int>(productService) {
    @ApiOperation("Create a product", notes = "Return a product")
    @PostMapping
    override fun save(@Valid @RequestBody body: Product): ResponseEntity<Product> {
        println("Creating product...")
        println(body)
        val productObject = Product(
            name = "Product 2",
            price = 0.0,
            stock = 1.0,
            provider = body.provider,
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