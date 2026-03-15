package reclutamiento.app.api_proceso.services.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;
import reclutamiento.app.api_proceso.mappers.ProcesoMapper;
import reclutamiento.app.api_proceso.publisher.rabbit.ProcesoPublisherService;
import reclutamiento.app.api_proceso.repositories.OrganoRepository;
import reclutamiento.app.api_proceso.repositories.ProcesoRepository;
import reclutamiento.app.api_proceso.resources.dtos.request.ProcesoRequest;
import reclutamiento.app.api_proceso.resources.dtos.response.PaginatedResponse;
import reclutamiento.app.api_proceso.resources.dtos.response.ProcesoResponse;
import reclutamiento.app.api_proceso.services.ProcesoService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository procesoRepository;
    private final OrganoRepository organoRepository;
    private final ProcesoMapper procesoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    //private  final ProcesoPublisherService  procesoPublisherService;

    @Override
    public PaginatedResponse<ProcesoResponse> getProcesoByPage(String filtro, int size, int page, String orderBy, String orderDir) {
        // orderDir: asc | desc
        var sort = orderDir.equalsIgnoreCase("desc")
                ? Sort.by(orderBy).descending()
                : Sort.by(orderBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        var pageResult = procesoRepository.getProcesoByPage(filtro, pageable);

        var content = pageResult
                .getContent()
                .stream()
                .map(procesoMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(content, pageResult.getTotalElements(), page, size);
    }

    @Override
    public Optional<ProcesoResponse> getById(UUID id) {
        return Optional.ofNullable(
                procesoRepository.findById(id)
                        .map(procesoMapper::toResponse)
                        .orElseThrow(() -> new RuntimeException("Proceso no encontrado"))
        );
    }

    @Transactional
    @Override
    public ProcesoResponse create(ProcesoRequest request) {
       // var procesoEntity = procesoMapper.toEntity(request);
        var procesoEntity = new ProcesoEntity();
        var organoEntity = organoRepository.findById(request.organoId())
                .orElseThrow(() -> new RuntimeException("Órgano y/o Unidad Organica no encontrada"));

        var listaProcesoComite = request.listaProcesoComite()
                .stream()
                .map(detalleRequest -> {
                    var organoDetalleEntity = organoRepository.findById(detalleRequest.organoId())
                            .orElseThrow(() -> new RuntimeException("Órgano y/o Unidad Organica no encontrada"));

                    return ProcesoComiteEntity.builder()
                            .proceso(procesoEntity)
                            .organo(organoDetalleEntity)
                            .nombre(detalleRequest.nombre())
                            .tipo(detalleRequest.tipo())
                            .email(detalleRequest.email())
                            .fechaCreacion(LocalDateTime.now())
                            .build();
                })
                .toList();

        procesoEntity.setNombre(request.nombre());
        procesoEntity.setResumen(request.resumen());
        procesoEntity.setTitulo(request.titulo());
        procesoEntity.setCantidad(request.cantidad());
        procesoEntity.setFechaInicio(request.fechaInicio());
        procesoEntity.setFechaFin(request.fechaFin());
        procesoEntity.setOrgano(organoEntity);
        procesoEntity.setListaProcesoComite(listaProcesoComite);
        procesoEntity.setFechaCreacion(LocalDateTime.now());
        procesoEntity.setEstado("REGISTRADO");

        var procesoGrabado = procesoRepository.save(procesoEntity);

        ProcesoResponse response = procesoMapper.toResponse(procesoGrabado);

        //procesoPublisherService.send(procesoMapper.toMessage(procesoGrabado));
        applicationEventPublisher.publishEvent(procesoMapper.toMessage(procesoGrabado));
        return response;
    }

    @Override
    public ProcesoResponse update(ProcesoRequest request, UUID id) {

        var procesoEntity = procesoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        var organoPrincipal = organoRepository.findById(request.organoId())
                .orElseThrow(() -> new RuntimeException("Órgano principal no encontrado"));

        procesoEntity.setNombre(request.nombre());
        procesoEntity.setResumen(request.resumen());
        procesoEntity.setTitulo(request.titulo());
        procesoEntity.setCantidad(request.cantidad());
        procesoEntity.setFechaInicio(request.fechaInicio());
        procesoEntity.setFechaFin(request.fechaFin());
        procesoEntity.setOrgano(organoPrincipal);
        procesoEntity.setFechaModificacion(LocalDateTime.now());
        //procesoEntity.getListaProcesoComite().clear();

        List<ProcesoComiteEntity> nuevosComites = request.listaProcesoComite()
                .stream()
                .map(detalle -> {
                    var organoDetalleEntity = organoRepository.findById(detalle.organoId())
                            .orElseThrow(() -> new RuntimeException("Órgano y/o Unidad Organica no encontrada"));

                    return ProcesoComiteEntity.builder()
                            .proceso(procesoEntity)
                            .organo(organoDetalleEntity)
                            .nombre(detalle.nombre())
                            .tipo(detalle.tipo())
                            .fechaCreacion(LocalDateTime.now())
                            .build();
                })
                .toList();

        procesoEntity.getListaProcesoComite().addAll(nuevosComites);

        var procesoActualizado = procesoRepository.save(procesoEntity);
        return procesoMapper.toResponse(procesoActualizado);
    }

    @Override
    public void delete(UUID id) {
        var procesoEntity = procesoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        procesoEntity.setEstado("ELIMINADO");
        procesoEntity.setFechaModificacion(LocalDateTime.now());

        procesoRepository.save(procesoEntity);
    }
}
