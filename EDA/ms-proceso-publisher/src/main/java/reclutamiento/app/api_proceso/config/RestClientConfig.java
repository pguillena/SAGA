package reclutamiento.app.api_proceso.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    @Bean("template")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean("restClient")
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl("http://localhost:8082/api/procesos").build();
    }
}
