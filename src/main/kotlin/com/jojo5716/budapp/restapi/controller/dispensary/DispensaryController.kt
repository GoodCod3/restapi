package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.DispensaryProductGeneticProfile
import com.jojo5716.budapp.restapi.domain.DispensarySetting
import com.jojo5716.budapp.restapi.service.DispensaryProductGeneticProfileService
import com.jojo5716.budapp.restapi.service.DispensarySettingService
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