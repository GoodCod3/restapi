package com.jojo5716.budapp.restapi.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int = 0,
    @get:Email
    var email: String,
    @get:Size(min = 3, max = 50)
    var name: String
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false
        other as User

        return this.email == other.email
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}