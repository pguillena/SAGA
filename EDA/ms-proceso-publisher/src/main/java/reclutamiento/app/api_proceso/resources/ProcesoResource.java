package reclutamiento.app.api_proceso.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reclutamiento.app.api_proceso.resources.dtos.request.OrganoRequest;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoResponse;
import reclutamiento.app.api_proceso.services.ProcesoService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/procesos")
@AllArgsConstructor
@Tag(
        name = "Procesos",
        description = "Gestión de Procesos de reclutamiento CAS de la Entidad ABC."
)
public class ProcesoResource {
    private final ProcesoService procesoService;


    @Operation(
            summary = "Listar procesos paginados",
            description = "Obtiene el listado paginado de los procesos de reclutamiento CAS, permitiendo filtros y ordenamiento.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrganoResponse[].class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No existen procesos"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<ProcesoResponse>> getProcesoByPage(
            @Parameter(description = "Texto de búsqueda por nombre o resumen")
            @RequestParam(defaultValue = "") String filtro,

            @Parameter(description = "Número de página (0-based)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de registros por página")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Campo por el cual ordenar")
            @RequestParam(defaultValue = "nombre") String orderBy,

            @Parameter(description = "Dirección del ordenamiento: asc | desc")
            @RequestParam(defaultValue = "asc") String orderDir
    ) {
        var response = procesoService.getProcesoByPage(filtro, size, page, orderBy, orderDir);

        if (response.content().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Registrar un proceso",
            description = "Permite registrar un nuevo proceso en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Proceso registrado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrganoResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de entrada inválidos"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ProcesoResponse> create(@Valid @RequestBody ProcesoRequest request) {
        var proceso = procesoService.create(request);
        return new ResponseEntity<>(proceso, CREATED);
    }

    @Operation(
            summary = "Actualizar un proceso",
            description = "Permite actualizar la información de un proceso de reclutamiento CAS.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proceso actualizado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrganoResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Proceso no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProcesoResponse> update(
            @Parameter(description = "ID del proceso a actualizar")
            @PathVariable UUID id,
            @Valid @RequestBody ProcesoRequest request
    ) {
        var proceso = procesoService.update(request, id);
        return ResponseEntity.ok(proceso);
    }

}
