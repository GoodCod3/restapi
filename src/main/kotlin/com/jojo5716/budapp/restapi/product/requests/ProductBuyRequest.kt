package com.jojo5716.budapp.restapi.product.requests

import javax.validation.constraints.Min

data class ProductBuyRequestParams(
    @get:Min(1)
    var id: Int = 0,
    @get:Min(0)
    var stock: Int,
)