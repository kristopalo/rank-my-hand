package com.bigstackbully.rankmyhand.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Rank My Hand API")
                    .version("1.0.0")
                    .description("API for evaluating and ranking poker hands. This application allows you to evaluate poker hands, retrieve card information, and explore hand rankings and combinations.")
                    .contact(
                        Contact()
                            .name("Kristo Palo")
                            .email("kristo.palo@gmail.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                    )
            )
    }
}
