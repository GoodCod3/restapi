package com.jojo5716.budapp.restapi.provider.controllers

import com.jojo5716.budapp.restapi.controller.BasicController
import com.jojo5716.budapp.restapi.provider.entities.Provider
import com.jojo5716.budapp.restapi.provider.services.ProviderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/provider")
class ProviderController(providerService: ProviderService) : BasicController<Provider, Int>(providerService)