package cl.lyriq.api_gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Lyriq API Gateway")
                                .version("1.0")
                                .description(
                                        "Gateway principal encargado de enrutar las solicitudes " +
                                        "a los microservicios de Lyriq. " +
                                        "Acceso a Swagger en: http://localhost:7070/swagger-ui.html"
                                )
                                .contact(
                                        new Contact()
                                                .name("Lyriq Team")
                                                .email("lyriq@gmail.com")
                                )
                );
    }
}