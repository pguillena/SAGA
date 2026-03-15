package reclutamiento.app.api_proceso.mongodb.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.mongodb.adapter.ProcesoComiteAdapter;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoComiteCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoComiteDocument;
import reclutamiento.app.api_proceso.mongodb.repository.ProcesoComiteMongoRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProcesoComiteCreateHandlerImpl implements ProcesoComiteCreateHandler {

    private final ProcesoComiteMongoRepository procesoComiteMongoRepository;
    private final ProcesoComiteAdapter procesoComiteAdapter;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void create(List<ProcesoComiteCreateCommand> commands) {

        List<ProcesoComiteDocument> documents = procesoComiteAdapter.toListDocument(commands);

        documents.forEach(doc -> doc.setFechaCreacion(LocalDateTime.now()));

        procesoComiteMongoRepository.saveAll(documents);

    }
}
