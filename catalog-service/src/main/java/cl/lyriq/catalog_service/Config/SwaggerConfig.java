package cl.lyriq.catalog_service.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                .addServersItem(
                        new Server()
                                .url("http://localhost:7070")
                                .description("API Gateway")
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )

                .info(
                        new Info()
                                .title("Lyriq Catalog Service API")
                                .version("1.0")
                                .description(
                                        "Microservicio encargado de gestionar canciones y géneros musicales")
                                .contact(
                                        new Contact()
                                                .name("Lyriq Team")
                                                .email("lyriq@gmail.com")
                                )
                );
    }
}