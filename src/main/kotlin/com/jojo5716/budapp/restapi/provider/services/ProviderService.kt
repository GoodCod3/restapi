package com.jojo5716.budapp.restapi.provider.services

import com.jojo5716.budapp.restapi.provider.repositories.ProviderDAO
import com.jojo5716.budapp.restapi.provider.entities.Provider
import com.jojo5716.budapp.restapi.services.BasicCRUD
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProviderService(val providerDAO: ProviderDAO) : BasicCRUD<Provider, Int> {
    override fun findAll(): List<Provider> = this.providerDAO.findAll()

    override fun findById(id: Int): Provider? = this.providerDAO.findByIdOrNull(id)

    override fun save(t: Provider): Provider {
        if (this.providerDAO.existsById(t.id)) {
            throw DuplicateKeyException("${t.id} does exist")
        }
        return this.providerDAO.save(t)
    }

    override fun update(t: Provider): Provider {
        if (this.providerDAO.existsById(t.id)) {
            return this.providerDAO.save(t)
        }

        throw EntityNotFoundException("${t.id} does exist")
    }

    override fun deleteById(id: Int): Provider {
        return this.findById(id)?.apply {
            this@ProviderService.providerDAO.deleteById(id)
        } ?: throw EntityNotFoundException("$id does not exist")
    }
}