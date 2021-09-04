package com.jojo5716.budapp.restapi.service

interface BasicCRUD<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(t:T): Boolean
    fun update(t:T): Boolean
    fun deleteById(id:ID): Boolean
}