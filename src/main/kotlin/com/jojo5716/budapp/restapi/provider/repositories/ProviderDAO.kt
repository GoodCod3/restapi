package com.jojo5716.budapp.restapi.provider.repositories

import com.jojo5716.budapp.restapi.provider.entities.Provider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProviderDAO : JpaRepository<Provider, Int>