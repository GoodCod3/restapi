package com.jojo5716.budapp.restapi.domain

import javax.validation.constraints.Min
import javax.validation.constraints.Size

class Provider(
    @get:Size(min = 3, max = 50)
    var name: String,
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Product

        return this.name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}