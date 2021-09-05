package com.jojo5716.budapp.restapi.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    @get:Size(min = 3, max = 50)
    var name: String,
    @get:Min(value = 0)
    var price: Double? = 0.0,
    @get:Min(value = 0)
    var stock: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Product

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}