package com.jojo5716.budapp.restapi.product.requests

import com.jojo5716.budapp.restapi.provider.entities.Provider
import javax.persistence.ManyToOne
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class CreateProductRequest(
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