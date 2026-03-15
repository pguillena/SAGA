package reclutamiento.app.api_proceso.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PageClientResponse<T>(
        List<T> content
) {}