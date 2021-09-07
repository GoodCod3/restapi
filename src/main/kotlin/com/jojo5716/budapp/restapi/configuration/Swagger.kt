package com.jojo5716.budapp.restapi.configuration

import com.jojo5716.budapp.restapi.RestapiApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger {
    @Bean
    fun api() : Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(RestapiApplication::class.java.`package`.name))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo());

    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Swagger By GoodCode")
            .version("1.0")
            .description("API restfull to manage a dispensary")
            .contact(Contact("Good Code", "http://www.goodcode.com", "info@goodcode.com"))
            .license("Apache License Version 2.0")
            .build()
    }
}