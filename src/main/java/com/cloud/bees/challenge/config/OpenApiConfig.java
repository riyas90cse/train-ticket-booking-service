package com.cloud.bees.challenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${app.url}")
    private String url;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("riyas90cse@gmail.com");
        contact.setName("CloudBees Interview Challenge");
        contact.setUrl("#");

        License license = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Train Ticket Booking Engine API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for Booking the Train Tickets")
                .termsOfService("#")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
