package com.autostock;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autoStockOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoStock API")
                        .description("Sistema de Control de Inventario AutoStock")
                        .version("1.0"));
    }
}
