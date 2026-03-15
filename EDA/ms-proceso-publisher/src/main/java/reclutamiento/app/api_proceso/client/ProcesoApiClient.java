package reclutamiento.app.api_proceso.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import reclutamiento.app.api_proceso.client.dto.PageClientResponse;
import reclutamiento.app.api_proceso.client.dto.ProcesoClientResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ProcesoApiClient {

    @Autowired
    @Qualifier("template")
    RestTemplate restTemplate;

    @Autowired
    RestClient restClient;

    public List<ProcesoClientResponse> getProcesosWithRestTemplate() {
        try {
            log.info("Consumiendo API Externo con RestTemplate...");

            String url = "http://localhost:8082/api/procesos/paginated?page=0&size=10&orderBy=nombre&orderDir=asc";

            ResponseEntity<PageClientResponse<ProcesoClientResponse>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<>() {}
                    );

            return Objects.requireNonNull(response.getBody()).content();

        } catch (RestClientException e) {
            log.error("Error al consumir API Externa con RestTemplate: ", e);
            return List.of();
        }
    }

    public List<ProcesoClientResponse> getProcesosWithRestClient() {
        try {
            log.info("Consumiendo API Externo con RestClient...");

            PageClientResponse<ProcesoClientResponse> response =
                    restClient.get()
                            .uri("/paginated?page=0&size=10&orderBy=nombre&orderDir=asc")
                            .retrieve()
                            .body(new ParameterizedTypeReference<>() {});

            return Objects.requireNonNull(response).content();

        } catch (RestClientException e) {
            log.error("Error al consumir API Externa con RestClient: ", e);
            return List.of();
        }
    }
}
