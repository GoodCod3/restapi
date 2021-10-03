package com.jojo5716.budapp.restapi.dispensary.requests

import com.jojo5716.budapp.restapi.product.entities.Product
import javax.persistence.*
import javax.validation.constraints.Min

data class CreateDispensaryProductGeneticProfileRequest(
    @get:Min(value = 0)
    var thc: Double,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "product_id")
    var product: Product,
)