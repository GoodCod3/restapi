package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(productService: ProductService): BasicController<Product, String>(productService)