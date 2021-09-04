package com.jojo5716.budapp.restapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.jojo5716.budapp.restapi.domain.Product
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
class RestapiApplicationTests {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var mapper:ObjectMapper

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }
    
    private val endpointBase = "/api/v1/products"
    
    @Test
    fun findAll() {
        val expectedBody = "[{'name':'Product 1','price':22.2},{'name':'Product 2','price':33.1}]"

        mockMvc.perform(MockMvcRequestBuilders.get(endpointBase))
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(expectedBody))
    }
    
    @Test
    fun findById() {
        val expectedResult = "{'name':'Product 1','price':22.2}"
        
        mockMvc.perform(MockMvcRequestBuilders.get("${endpointBase}/Product 1"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(expectedResult))
    }

    @Test
    fun findByIdEmpty() {
        mockMvc.perform(MockMvcRequestBuilders.get("${endpointBase}/Unavailable Product"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("&").doesNotExist())
    }

    @Test
    fun saveSuccessfully() {
        val product = Product(name = "Apple Iphone X", price = 1000.0)

        val result:Boolean = mockMvc.perform(MockMvcRequestBuilders.post(endpointBase)
            .content(mapper.writeValueAsBytes(product))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)

            assert(result)
    }
}
