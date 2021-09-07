package com.jojo5716.budapp.restapi.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class DispensarySetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @get:Size(min = 3, max = 100)
    var name: String,
)
