package com.jojo5716.budapp.restapi.dispensary.controllers

import com.jojo5716.budapp.restapi.controllers.BasicController
import com.jojo5716.budapp.restapi.dispensary.entities.DispensaryProductGeneticProfile
import com.jojo5716.budapp.restapi.services.DispensaryProductGeneticProfileService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/dispensary/product/genetic/profile")
class DispensaryProductGeneticProfileController(dispensaryProductGeneticProfileService: DispensaryProductGeneticProfileService) :
    BasicController<DispensaryProductGeneticProfile, Int>(dispensaryProductGeneticProfileService)