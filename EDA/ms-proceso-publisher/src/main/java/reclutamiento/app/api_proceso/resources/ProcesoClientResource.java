package reclutamiento.app.api_proceso.resources;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reclutamiento.app.api_proceso.client.ProcesoApiClient;
import reclutamiento.app.api_proceso.client.dto.ProcesoClientResponse;

import java.util.List;

@RestController
@RequestMapping("/api/client/procesos")
@AllArgsConstructor
public class ProcesoClientResource {
    private final ProcesoApiClient apiClient;


    @GetMapping("/rest-template")
    public ResponseEntity<List<ProcesoClientResponse>> getProcesosWithRestTemplate() {
        var procesos = apiClient.getProcesosWithRestTemplate();
        if (procesos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(procesos);
    }

    @GetMapping("/rest-client")
    public ResponseEntity<List<ProcesoClientResponse>> getProcesosWithRestClient() {
        var procesos = apiClient.getProcesosWithRestClient();
        if (procesos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(procesos);
    }



}
