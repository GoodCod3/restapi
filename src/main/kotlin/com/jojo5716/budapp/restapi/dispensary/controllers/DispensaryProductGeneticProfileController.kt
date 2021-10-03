package com.jojo5716.budapp.restapi.dispensary.controllers

import com.jojo5716.budapp.restapi.controllers.BasicController
import com.jojo5716.budapp.restapi.dispensary.entities.DispensaryProductGeneticProfile
import com.jojo5716.budapp.restapi.dispensary.requests.CreateDispensaryProductGeneticProfileRequest
import com.jojo5716.budapp.restapi.product.entities.Product
import com.jojo5716.budapp.restapi.product.services.ProductService
import com.jojo5716.budapp.restapi.services.DispensaryProductGeneticProfileService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/dispensary/product/genetic/profile")
class DispensaryProductGeneticProfileController(
    private val dispensaryProductGeneticProfileService: DispensaryProductGeneticProfileService,
    private val productService: ProductService
) :
    BasicController<DispensaryProductGeneticProfile, Int>(dispensaryProductGeneticProfileService) {

    @ApiOperation("Create a product", notes = "Return a product")
    @PostMapping
    override fun save(@Valid @RequestBody body: DispensaryProductGeneticProfile): ResponseEntity<DispensaryProductGeneticProfile?> {
        println("Creating DispensaryProductGeneticProfile...")
        println(body)

//        productService.findById(body.product.id)?.let {
        productService.findAll().first().let {
            val productObject = DispensaryProductGeneticProfile(
                thc = body.thc,
                product = it,
            )

            val product = dispensaryProductGeneticProfileService.save(productObject)

            return ResponseEntity.status(HttpStatus.CREATED).body(product)
        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(null)
    }
}