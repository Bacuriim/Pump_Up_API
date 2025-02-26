package org.pump.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Pump API")
						.version("1.0")
						.description("API para gerenciamento de usuários e medidores")
						.contact(new Contact()
								.name("Seu Nome")
								.email("seuemail@exemplo.com")
								.url("https://seusite.com")
						)
				)
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new io.swagger.v3.oas.models.Components()
						.addSecuritySchemes("bearerAuth",
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")))
				.servers(List.of(
						new Server().url("http://localhost:2000").description("Servidor Local"),
						new Server().url("https://api.seudominio.com").description("Servidor de Produção")
				));
		
			}
		}
