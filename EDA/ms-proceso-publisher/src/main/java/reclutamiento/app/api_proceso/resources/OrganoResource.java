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
import reclutamiento.app.api_proceso.resources.dtos.response.OrganoResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;
import reclutamiento.app.api_proceso.services.OrganoService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/organos")
@AllArgsConstructor
@Tag(
        name = "Órganos",
        description = "Gestión de Órganos y Unidades Orgánicas de la Entidad ABC."
)
public class OrganoResource {
    private final OrganoService organoService;

    @Operation(
            summary = "Listar órganos paginados",
            description = "Obtiene el listado paginado de órganos y/o unidades orgánicas, permitiendo filtros y ordenamiento.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrganoResponse[].class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No existen organos"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<OrganoResponse>> getOrganoByPage(
            @Parameter(description = "Texto de búsqueda por nombre o código")
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
        var response = organoService.getOrganoByPage(filtro, size, page, orderBy, orderDir);

        if (response.content().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Registrar un órgano",
            description = "Permite registrar un nuevo órgano o unidad orgánica en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Órgano registrado correctamente",
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
    public ResponseEntity<OrganoResponse> create(@Valid @RequestBody OrganoRequest request) {
        var organo = organoService.create(request);
        return new ResponseEntity<>(organo, CREATED);
    }

    @Operation(
            summary = "Actualizar un órgano",
            description = "Permite actualizar la información de un órgano o unidad orgánica existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Órgano actualizado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrganoResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Órgano no encontrado"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<OrganoResponse> update(
            @Parameter(description = "ID del órgano a actualizar")
            @PathVariable UUID id,
            @Valid @RequestBody OrganoRequest request
    ) {
        var organo = organoService.update(request, id);
        return ResponseEntity.ok(organo);
    }
}
