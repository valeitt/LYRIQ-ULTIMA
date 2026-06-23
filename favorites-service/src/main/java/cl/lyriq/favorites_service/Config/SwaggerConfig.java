package cl.lyriq.favorites_service.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

            .servers(List.of(
                        new Server()
                                .url("http://localhost:7070")
                                .description("API Gateway")
                ))

                .info(new Info()

                        .title("Lyriq Favorites Service API")

                        .version("1.0")

                        .description(
                                "Microservicio encargado de gestionar las canciones favoritas de los usuarios")

                        .contact(
                                new Contact()
                                        .name("Lyriq Team")
                                        .email("lyriq@gmail.com")
                        )
                );
    }
}