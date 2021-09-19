package com.jojo5716.budapp.restapi.dispensary.repositories

import com.jojo5716.budapp.restapi.domain.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DispensarySettingDAO : JpaRepository<DispensarySetting, Int>

