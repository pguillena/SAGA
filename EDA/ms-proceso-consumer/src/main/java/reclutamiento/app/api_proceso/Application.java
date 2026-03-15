package reclutamiento.app.api_proceso;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {
	@Bean
	public OpenAPI customeOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API PROCESO PARA RECLUTAMIENTO DE PERSONAL")
						.version("1.0.0")
						.summary("API para procesos de postulación")
						.description("Esta API crea los procesos de postulación CAS para el el reclutamiento de personal"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
