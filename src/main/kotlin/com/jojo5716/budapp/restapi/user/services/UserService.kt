package com.jojo5716.budapp.restapi.user.services

import com.jojo5716.budapp.restapi.service.BasicCRUD
import com.jojo5716.budapp.restapi.user.repositories.UserDAO
import com.jojo5716.budapp.restapi.user.entities.User
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(val userDAO: UserDAO) : BasicCRUD<User, Int> {
    override fun findAll(): List<User> = this.userDAO.findAll()

    override fun findById(id: Int): User? = this.userDAO.findByIdOrNull(id)

    override fun save(t: User): User {
        if (this.userDAO.existsByEmail(t.email)) {
            throw DuplicateKeyException("${t.name} does exist")
        }
        println(t)
        return this.userDAO.save(t)
    }

    override fun update(t: User): User {
        if (this.userDAO.existsById(t.id)) {
            return this.userDAO.save(t)
        }

        throw EntityNotFoundException("${t.name} does exist")
    }

    override fun deleteById(id: Int): User {
        return this.findById(id)?.apply {
            this@UserService.userDAO.deleteById(id)
        } ?: throw EntityNotFoundException("$id does not exist")
    }
}