package com.jojo5716.budapp.restapi.product.entities

import com.jojo5716.budapp.restapi.domain.Provider
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @get:Size(min = 3, max = 50)
    var name: String,
    @get:Min(value = 0)
    var price: Double = 0.0,
    @get:Min(value = 0)
    var stock: Double = 0.0,
    @ManyToOne
    var provider: Provider,
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

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(name = $name - id: $id)"
    }
}