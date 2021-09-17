package com.jojo5716.budapp.restapi.domain

import javax.persistence.*

@Entity
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var product: Product?,
){
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Purchase

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(product = $product - id: $id)"
    }
}