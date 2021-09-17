package com.jojo5716.budapp.restapi.domain

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
data class Product(
    @Id
    @get:Size(min = 3, max = 50)
    var name: String,
    @get:Min(value = 0)
    var price: Double = 0.0,
    @get:Min(value = 0)
    var stock: Double = 0.0,
    @ManyToOne
    var provider: Provider,
    @OneToOne
    var dispensaryGeneticProductProfile: DispensaryGeneticProductProfile?,
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