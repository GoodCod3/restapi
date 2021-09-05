package com.jojo5716.budapp.restapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jojo5716.budapp.restapi.domain.Product
import com.jojo5716.budapp.restapi.domain.Provider
import com.jojo5716.budapp.restapi.service.ProductService
import com.jojo5716.budapp.restapi.service.ProviderService
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
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class RestapiApplicationTests {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var providerService: ProviderService

    @Autowired
    private lateinit var mapper: ObjectMapper

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }

    private val endpointBase = "/api/v1/products"
    private val H2_BACKUP_LOCATION = "/tmp/h2backup.sql"

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
        val defaultProvider = providerService.save(Provider(name = "Default provider", email = "default@provider.com"))
        val product = Product(name = "AppleNew", price = 1000.0, stock = 3, provider = defaultProvider)

        val productFromApi: Product = mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().isCreated)
            .bodyTo(mapper)

        assertThat(productService.findById(product.id), Matchers.`is`(productFromApi))
    }

    @Test
    fun saveFailIfNameAndPriceAreInvalid() {
        val defaultProvider = providerService.save(Provider(name = "Default provider", email = "default@provider.com"))
        val product = Product(name = "", price = -1000.0, provider = defaultProvider)

        mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.price").exists())
    }

    @Test
    fun saveDuplicateEntity() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val firstProduct = productsFromService.first()

        mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(firstProduct, mapper)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title", Matchers.`is`("DuplicateKeyException")))
            .andExpect(jsonPath("$.message").exists())
    }

    @Test
    fun saveProductWithInvalidProvider() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = Product(
            name = "AppleTest",
            price = 22.2,
            stock = 4,
            provider = Provider(id = 50, name = "Test", email = "test@test.com")
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post(endpointBase)
                .body(data = product, mapper)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title", Matchers.`is`("JpaObjectRetrievalFailureException")))
            .andExpect(jsonPath("$.message").exists())
    }

    @Test
    fun updateSuccessfully() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = productsFromService.first().copy(price = 1000.0)

        val result: Product = mockMvc.perform(
            MockMvcRequestBuilders.put(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)
    }

    @Test
    fun updateEntityNotFound() {
        val defaultProvider = providerService.save(Provider(name = "Default provider", email = "default@provider.com"))
        val product = Product(name = "Unavailable product", price = 123.45, provider = defaultProvider)

        mockMvc.perform(
            MockMvcRequestBuilders.put(endpointBase)
                .body(product, mapper)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title", Matchers.`is`("EntityNotFoundException")))
            .andExpect(jsonPath("$.message").exists())
    }

    @Test
    fun deleteByIdSuccessfully() {
        val productsFromService: List<Product> = productService.findAll()

        assert(!productsFromService.isEmpty()) { "Should not be empty" }

        val product = productsFromService.last()

        val productFromApi: Product = mockMvc.perform(
            MockMvcRequestBuilders.delete("${endpointBase}/${product.name}")
        )
            .andExpect(status().is2xxSuccessful)
            .bodyTo(mapper)

        assert(!productService.findAll().contains(productFromApi))
    }

    @Test
    fun deleteByIdEntityNotFound() {
        val defaultProvider = providerService.save(Provider(name = "Default provider", email = "default@provider.com"))
        val product = Product(name = "Unavailable product", price = 123.45, provider = defaultProvider)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("${endpointBase}/${product.name}")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title", Matchers.`is`("EntityNotFoundException")))
            .andExpect(jsonPath("$.message").exists())
    }
}
