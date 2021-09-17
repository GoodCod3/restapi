package com.jojo5716.budapp.restapi.domain

import javax.persistence.*

@Entity
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @ManyToOne
    var product: Product?,
)