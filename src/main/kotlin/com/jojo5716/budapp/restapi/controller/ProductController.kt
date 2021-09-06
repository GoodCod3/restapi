package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.service.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min


data class ProductBuyRequestParams(
    @get:Min(1)
    var id: String = "",
    @get:Min(0)
    var stock: Int,
)

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService) : BasicController<Product, String>(productService) {
    @ApiOperation("Buy a product", notes = "Buy a product if has a stock")
    @PostMapping("/buy/{id}")
    fun buy(@RequestBody body: ProductBuyRequestParams): ResponseEntity<Boolean> {
        productService.buy(body)

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}