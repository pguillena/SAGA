package reclutamiento.app.api_proceso.command.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.command.dto.command.CreateProcesoCommand;
import reclutamiento.app.api_proceso.command.dto.command.DeleteProcesoCommand;
import reclutamiento.app.api_proceso.command.publisher.ProcesoEventPublisher;
import reclutamiento.app.api_proceso.entities.OrganoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoStatus;
import reclutamiento.app.api_proceso.repositories.OrganoRepository;
import reclutamiento.app.api_proceso.repositories.ProcesoComiteRepository;
import reclutamiento.app.api_proceso.repositories.ProcesoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProcesoCommandService {

    private final ProcesoRepository repository;
    private final ProcesoComiteRepository procesoComiteRepository;
    private final OrganoRepository organoRepository;
    private final ProcesoEventPublisher publisher;

    @Transactional
    public void createProceso(CreateProcesoCommand command) {

        try {

            ProcesoEntity proceso = new ProcesoEntity();
            OrganoEntity organo = organoRepository
                    .findById(command.getOrganoId())
                    .orElseThrow();

            var listaProcesoComite = command.getListaProcesoComite()
                    .stream()
                    .map(detalleRequest -> {
                        var organoDetalleEntity = organoRepository.findById(detalleRequest.organoId())
                                .orElseThrow(() -> new RuntimeException("Órgano y/o Unidad Organica no encontrada"));

                        return ProcesoComiteEntity.builder()
                                .proceso(proceso)
                                .organo(organoDetalleEntity)
                                .nombre(detalleRequest.nombre())
                                .tipo(detalleRequest.tipo())
                                .email(detalleRequest.email())
                                .fechaCreacion(LocalDateTime.now())
                                .build();
                    })
                    .toList();

            proceso.setNombre(command.getNombre());
            proceso.setResumen(command.getResumen());
            proceso.setTitulo(command.getTitulo());
            proceso.setOrgano(organo);
            proceso.setCantidad(command.getCantidad());
            proceso.setFechaInicio(command.getFechaInicio());
            proceso.setFechaFin(command.getFechaFin());
            proceso.setFechaCreacion(LocalDateTime.now());
            proceso.setFechaFin(command.getFechaFin());
            proceso.setEstado(String.valueOf(ProcesoStatus.ACTIVE));
            proceso.setListaProcesoComite(listaProcesoComite);

            ProcesoEntity saved = repository.save(proceso);

            publisher.publishProcesoCreated(saved, command.getSagaId());

        } catch (Exception ex) {

            publisher.publishProcesoCreationFailed(
                    command.getSagaId(),
                    ex.getMessage()
            );
        }
    }

    @Transactional
    public void deleteProceso(DeleteProcesoCommand command) {

        // Warning - Delete Logic
        repository.deleteById(command.getProcesoId());

        publisher.publishProcesoDeleted(
                command.getProcesoId(),
                command.getSagaId()
        );
    }
}