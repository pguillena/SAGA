package reclutamiento.app.api_proceso.command.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.command.dto.command.CreateProcesoComiteCommand;
import reclutamiento.app.api_proceso.command.publisher.ProcesoEventPublisher;
import reclutamiento.app.api_proceso.entities.OrganoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;
import reclutamiento.app.api_proceso.entities.ProcesoStatus;
import reclutamiento.app.api_proceso.mongodb.adapter.ProcesoComiteAdapter;
import reclutamiento.app.api_proceso.mongodb.handler.ProcesoComiteCreateHandler;
import reclutamiento.app.api_proceso.repositories.OrganoRepository;
import reclutamiento.app.api_proceso.repositories.ProcesoComiteRepository;
import reclutamiento.app.api_proceso.repositories.ProcesoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcesoComiteCommandService {

    private final ProcesoRepository repository;
    private final ProcesoComiteRepository procesoComiteRepository;
    private final OrganoRepository organoRepository;
    private final ProcesoEventPublisher publisher;
    private final ProcesoComiteAdapter adapter;
    private final ProcesoComiteCreateHandler procesoComiteCreateHandler;



    @Transactional
    public void createProcesoComite(CreateProcesoComiteCommand command) {

        ProcesoEntity proceso = repository.findById(command.getProcesoId()).orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        try {
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

            procesoComiteRepository.saveAll(listaProcesoComite);
            procesoComiteCreateHandler.create(adapter.entityToListCommand(listaProcesoComite));
            publisher.publishProcesoComiteCreated(command.getSagaId(), command.getProcesoId());

        } catch (Exception ex) {

            publisher.publishProcesoComiteCreationFailed(
                    command.getSagaId(),
                    ex.getMessage()
            );
        }
    }


}