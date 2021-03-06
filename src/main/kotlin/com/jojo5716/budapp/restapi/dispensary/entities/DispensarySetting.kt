package com.jojo5716.budapp.restapi.domain

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
data class DispensarySetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @get:Size(min = 3, max = 100)
    var name: String,
)
