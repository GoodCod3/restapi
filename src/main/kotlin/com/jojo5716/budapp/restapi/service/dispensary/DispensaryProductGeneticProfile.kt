package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.dao.DispensaryProductGeneticProfileDAO
import com.jojo5716.budapp.restapi.domain.DispensaryProductGeneticProfile
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class DispensaryProductGeneticProfileService(val dispensaryProductGeneticProfileDAO: DispensaryProductGeneticProfileDAO) :
    BasicCRUD<DispensaryProductGeneticProfile, Int> {
    override fun findAll(): List<DispensaryProductGeneticProfile> = this.dispensaryProductGeneticProfileDAO.findAll()

    override fun findById(id: Int): DispensaryProductGeneticProfile? =
        this.dispensaryProductGeneticProfileDAO.findByIdOrNull(id)

    override fun save(t: DispensaryProductGeneticProfile): DispensaryProductGeneticProfile {
        if (this.dispensaryProductGeneticProfileDAO.existsById(t.id)) {
            throw DuplicateKeyException("${t.id} does exist")
        }
        return this.dispensaryProductGeneticProfileDAO.save(t)
    }

    override fun update(t: DispensaryProductGeneticProfile): DispensaryProductGeneticProfile {
        if (this.dispensaryProductGeneticProfileDAO.existsById(t.id)) {
            return this.dispensaryProductGeneticProfileDAO.save(t)
        }

        throw EntityNotFoundException("${t.id} does exist")
    }

    override fun deleteById(id: Int): DispensaryProductGeneticProfile {
        return this.findById(id)?.apply {
            this@DispensaryProductGeneticProfileService.dispensaryProductGeneticProfileDAO.deleteById(id)
        } ?: throw EntityNotFoundException("${id} does not exist")
    }
}