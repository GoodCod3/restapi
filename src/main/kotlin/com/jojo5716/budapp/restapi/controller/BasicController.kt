package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.service.BasicCRUD
import org.springframework.web.bind.annotation.*

abstract class BasicController<T, ID>(private val basicCRUD: BasicCRUD<T, ID>) {
    @GetMapping
    fun findAll() = basicCRUD.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID) = basicCRUD.findById(id)

    @PostMapping
    fun save(@RequestBody body: T) = basicCRUD.save(body)

    @PutMapping
    fun update(@RequestBody body: T) = basicCRUD.update(body)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ID) = basicCRUD.deleteById(id)
}