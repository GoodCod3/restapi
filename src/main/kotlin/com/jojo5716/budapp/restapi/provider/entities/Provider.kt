package com.jojo5716.budapp.restapi.provider.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
data class Provider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int = 0,
    @get:Size(min = 3, max = 50)
    var name: String,
    @get:Email
    var email: String,
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as Provider

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