package com.jojo5716.budapp.restapi.domain

data class Product(val name: String, val price: Double? = 0.0) {
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