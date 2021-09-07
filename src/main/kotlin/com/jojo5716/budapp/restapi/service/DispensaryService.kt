package com.jojo5716.budapp.restapi.service

import com.jojo5716.budapp.restapi.dao.DispensarySettingDAO
import com.jojo5716.budapp.restapi.domain.DispensarySetting
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class DispensarySettingService(val dispensarySettingDAO: DispensarySettingDAO) : BasicCRUD<DispensarySetting, Int> {
    override fun findAll(): List<DispensarySetting> = this.dispensarySettingDAO.findAll()

    override fun findById(id: Int): DispensarySetting? = this.dispensarySettingDAO.findByIdOrNull(id)

    override fun save(t: DispensarySetting): DispensarySetting {
        if (this.dispensarySettingDAO.existsById(t.id)) {
            throw DuplicateKeyException("${t.id} does exist")
        }
        return this.dispensarySettingDAO.save(t)
    }

    override fun update(t: DispensarySetting): DispensarySetting {
        if (this.dispensarySettingDAO.existsById(t.id)) {
            return this.dispensarySettingDAO.save(t)
        }

        throw EntityNotFoundException("${t.id} does exist")
    }

    override fun deleteById(id: Int): DispensarySetting {
        return this.findById(id)?.apply {
            this@DispensarySettingService.dispensarySettingDAO.deleteById(id)
        } ?: throw EntityNotFoundException("${id} does not exist")
    }
}