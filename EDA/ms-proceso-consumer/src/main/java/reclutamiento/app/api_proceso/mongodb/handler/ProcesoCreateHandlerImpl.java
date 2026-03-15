package reclutamiento.app.api_proceso.mongodb.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.mongodb.adapter.ProcesoAdapter;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.mongodb.repository.ProcesoMongoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProcesoCreateHandlerImpl implements ProcesoCreateHandler {

    private final ProcesoMongoRepository procesoMongoRepository;
    private final ProcesoAdapter procesoAdapter;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void create(ProcesoCreateCommand command) {

        ProcesoDocument procesoDocument = procesoAdapter.toDocument(command);
        procesoDocument.setFechaCreacion(LocalDateTime.now());
        procesoMongoRepository.save(procesoDocument);

        applicationEventPublisher.publishEvent(command);

    }
}
