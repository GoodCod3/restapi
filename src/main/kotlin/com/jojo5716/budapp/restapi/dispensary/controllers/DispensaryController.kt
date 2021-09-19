package com.jojo5716.budapp.restapi.controllers

import com.jojo5716.budapp.restapi.dispensary.entities.DispensaryProductGeneticProfile
import com.jojo5716.budapp.restapi.domain.DispensarySetting
import com.jojo5716.budapp.restapi.services.DispensaryProductGeneticProfileService
import com.jojo5716.budapp.restapi.services.DispensarySettingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/dispensary/setting")
class DispensarySettingController(dispensarySettingService: DispensarySettingService) :
    BasicController<DispensarySetting, Int>(dispensarySettingService)

@RestController
@RequestMapping("/api/v1/dispensary/product/genetic/profile")
class DispensaryProductGeneticProfileController(dispensaryProductGeneticProfileService: DispensaryProductGeneticProfileService) :
    BasicController<DispensaryProductGeneticProfile, Int>(dispensaryProductGeneticProfileService)