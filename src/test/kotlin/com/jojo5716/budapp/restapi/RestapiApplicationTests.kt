package com.jojo5716.budapp.restapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.service.ProductService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.regex.Matcher

@SpringBootTest
class RestapiApplicationTests {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var mapper: ObjectMapper

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }

    private val endpointBase = "/api/v1/products"

    @Test
    fun findAll() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val json: String = mockMvc.perform(MockMvcRequestBuilders.get(endpointBase))
            .andExpect(status().is2xxSuccessful)
            .andReturn().response.contentAsString

        val products: List<Product> = mapper.readValue(json)

        assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
    }

    @Test
    fun findById() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = productsFromService.first()

        mockMvc.perform(MockMvcRequestBuilders.get("${endpointBase}/${product.name}"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("$.name", Matchers.`is`(product.name)))
    }

    @Test
    fun findByIdEmpty() {
        mockMvc.perform(MockMvcRequestBuilders.get("${endpointBase}/Unavailable Product"))
            .andExpect(status().isNoContent)
            .andExpect(jsonPath("&").doesNotExist())
    }

    @Test
    fun saveSuccessfully() {
        val product = Product(name = "Apple Iphone X", price = 1000.0)

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)

        assert(result)
    }

    @Test
    fun saveFail() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val firstProduct = productsFromService.first()

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(firstProduct, mapper)
        )
            .andExpect(status().isConflict)
            .bodyTo(mapper)

        assert(!result) { "Should be false" }
    }

    @Test
    fun updateSuccessfully() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = productsFromService.first().copy(price = 1000.0)

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.put(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)

        assert(result)
    }

    @Test
    fun updateFail() {
        val product = Product(name = "Unavailable product", price = 123.45)

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.put(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().isConflict)
            .bodyTo(mapper)

        assert(!result) { "Should be false" }
    }

    @Test
    fun deleteByIdSuccessfully() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = productsFromService.last()

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.delete("${endpointBase}/${product.name}")
        )
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)

        assert(result)
    }

    @Test
    fun deleteByIdFail() {
        val product = Product(name = "Unavailable product", price = 123.45)

        val result: Boolean = mockMvc.perform(
            MockMvcRequestBuilders.delete("${endpointBase}/${product.name}")
        )
            .andExpect(status().isConflict)
            .bodyTo(mapper)

        assert(!result)
    }
}
