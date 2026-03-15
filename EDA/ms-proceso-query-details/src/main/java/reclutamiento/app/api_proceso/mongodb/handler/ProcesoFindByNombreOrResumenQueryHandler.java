package reclutamiento.app.api_proceso.mongodb.handler;

import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.mongodb.query.ProcesoFiltroRequestDto;
import reclutamiento.app.api_proceso.mongodb.repository.ProcesoMongoRepository;

import java.util.List;

@Service
public class ProcesoFindByNombreOrResumenQueryHandler implements ProcesoFindByNombreOrResumenQuery {

    private final ProcesoMongoRepository procesoMongoRepository;

    public ProcesoFindByNombreOrResumenQueryHandler(ProcesoMongoRepository procesoMongoRepository) {
        this.procesoMongoRepository = procesoMongoRepository;
    }

    @Override
    public List<ProcesoDocument> findByNombreOrResumenQuery(ProcesoFiltroRequestDto filtroRequestDto) {
        return procesoMongoRepository.findByLikeNombreOrResumen(filtroRequestDto.filtro());
    }
}
