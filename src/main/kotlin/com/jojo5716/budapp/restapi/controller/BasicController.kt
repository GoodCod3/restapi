package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.service.BasicCRUD
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

abstract class BasicController<T, ID>(private val basicCRUD: BasicCRUD<T, ID>) {
    @ApiOperation("Get all entities")
    @GetMapping
    fun findAll(): ResponseEntity<List<T>> {
        val entities = this.basicCRUD.findAll()

        return ResponseEntity.status(HttpStatus.OK).body(entities)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID): ResponseEntity<T> {
        val entity = this.basicCRUD.findById(id)

        return ResponseEntity.status(if (entity != null) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)
    }

    @PostMapping
    fun save(@Valid @RequestBody body: T) = ResponseEntity.status(HttpStatus.CREATED).body(this.basicCRUD.save(body))

    @PutMapping
    fun update(@RequestBody body: T) = this.basicCRUD.update(body)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ID) = this.basicCRUD.deleteById(id)

}