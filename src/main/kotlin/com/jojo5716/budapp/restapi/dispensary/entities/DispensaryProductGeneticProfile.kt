package com.jojo5716.budapp.restapi.dispensary.entities

import com.jojo5716.budapp.restapi.product.entities.Product
import javax.persistence.*
import javax.validation.constraints.Min


@Entity
data class DispensaryProductGeneticProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @get:Min(value = 0)
    var thc: Double,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "product_id")
    var product: Product,
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as DispensaryProductGeneticProfile

        return this.product == other.product && id == other.id
    }

    override fun hashCode(): Int {
        return product.name.hashCode()
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(product = $product - THC: $thc)"
    }
}
