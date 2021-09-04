package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.service.BasicCRUD
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class BasicController<T, ID>(private val basicCRUD: BasicCRUD<T, ID>) {
    @GetMapping
    fun findAll(): ResponseEntity<List<T>> {
        val entities = basicCRUD.findAll()

        return ResponseEntity.status(HttpStatus.OK).body(entities)

    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID): ResponseEntity<T> {
        val entity = basicCRUD.findById(id)

        return ResponseEntity.status(if (entity != null) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)

    }

    @PostMapping
    fun save(@RequestBody body: T): ResponseEntity<Boolean> {
        val entity = basicCRUD.save(body)

        return ResponseEntity.status(if (entity) HttpStatus.CREATED else HttpStatus.CONFLICT).body(entity)
    }

    @PutMapping
    fun update(@RequestBody body: T): ResponseEntity<Boolean> {
        val updated: Boolean = basicCRUD.update(body)

        return ResponseEntity.status(if (updated) HttpStatus.OK else HttpStatus.CONFLICT).body(updated)

    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ID): ResponseEntity<Boolean> {
        val deleted: Boolean = basicCRUD.deleteById(id)

        return ResponseEntity.status(if (deleted) HttpStatus.OK else HttpStatus.CONFLICT).body(deleted)

    }
}