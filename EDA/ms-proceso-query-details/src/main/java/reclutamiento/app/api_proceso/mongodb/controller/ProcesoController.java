package reclutamiento.app.api_proceso.mongodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.mongodb.handler.ProcesoFindByNombreOrResumenQuery;
import reclutamiento.app.api_proceso.mongodb.query.ProcesoFiltroRequestDto;

import java.util.List;

@RestController
@RequestMapping("/v1/proceso/query")
public class ProcesoController {

    private final ProcesoFindByNombreOrResumenQuery procesoFindByNombreOrResumenQuery;

    public ProcesoController(ProcesoFindByNombreOrResumenQuery procesoFindByNombreOrResumenQuery) {
        this.procesoFindByNombreOrResumenQuery = procesoFindByNombreOrResumenQuery;
    }

    @GetMapping("/by-filtro")
    public ResponseEntity<?> findByLikeNombreOrResumen(@RequestParam(value = "filtro", defaultValue = "") String filtro){
        ProcesoFiltroRequestDto filtroDto = new ProcesoFiltroRequestDto(filtro);
        List<ProcesoDocument> lstPedidoResumenDocument= procesoFindByNombreOrResumenQuery.findByNombreOrResumenQuery(filtroDto);
        if (lstPedidoResumenDocument.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lstPedidoResumenDocument);

    }
}
