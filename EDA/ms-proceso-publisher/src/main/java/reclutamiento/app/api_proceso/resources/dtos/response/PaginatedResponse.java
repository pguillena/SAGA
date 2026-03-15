package reclutamiento.app.api_proceso.resources.dtos.response;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> content,
        long totalElements,
        int page,
        int size
) {
}
