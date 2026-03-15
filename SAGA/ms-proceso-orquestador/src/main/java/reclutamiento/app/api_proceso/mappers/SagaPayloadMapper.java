package reclutamiento.app.api_proceso.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.resources.dtos.saga.CreateProcesoSagaPayload;
import reclutamiento.app.api_proceso.services.ProcesoSagaException;

import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_DESERIALIZING_PAYLOAD;
import static reclutamiento.app.api_proceso.messages.ProductSagaMessages.ERROR_SERIALIZING_PAYLOAD;

@Component
@RequiredArgsConstructor
public class SagaPayloadMapper {

    private final ObjectMapper objectMapper;

    public String toJson(CreateProcesoSagaPayload payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (Exception e) {
            throw new ProcesoSagaException(ERROR_SERIALIZING_PAYLOAD, e);
        }
    }

    public CreateProcesoSagaPayload fromJson(String json) {
        try {
            return objectMapper.readValue(json, CreateProcesoSagaPayload.class);
        } catch (Exception e) {
            throw new ProcesoSagaException(ERROR_DESERIALIZING_PAYLOAD, e);
        }
    }
}