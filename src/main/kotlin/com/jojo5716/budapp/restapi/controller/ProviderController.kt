package com.jojo5716.budapp.restapi.controller

import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.service.ProviderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/provider")
class ProviderController(providerService: ProviderService) : BasicController<Provider, Int>(providerService)