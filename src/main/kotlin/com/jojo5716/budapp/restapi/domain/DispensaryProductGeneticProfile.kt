package com.jojo5716.budapp.restapi.domain

import javax.persistence.*
import javax.validation.constraints.Min


@Entity
data class DispensaryProductGeneticProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @get:Min(value = 0)
    var thc: Double,
    @OneToOne
    var product: Product,
){
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as DispensaryProductGeneticProfile

        return this.product == other.product && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(product = $product - THC: $thc)"
    }
}
