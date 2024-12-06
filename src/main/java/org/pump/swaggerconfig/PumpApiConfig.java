package org.pump.swaggerconfig;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PumpApiConfig {

	Contact contact = new Contact();
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API Documentation of a Water Meter to Water Reservoirs")
						.version("1.0")
						.description("### Made by Conrado Einstein, Charles Lima and João Marcelo Pimenta\n\nThis is the API documentation for our project\n\n Request body example: \n\n{\n\n" +
								"    \"id\": 1,\n\n" +
								"    \"meter\": \"Casa\"," +
								"\n\n}\n\n\n\n").contact(contact.email("conradoeinsteinpro@gmail.com").url("https://github.com/Bacuriim")));
	}
}
