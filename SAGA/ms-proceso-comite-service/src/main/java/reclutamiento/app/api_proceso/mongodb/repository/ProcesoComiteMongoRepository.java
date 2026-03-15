package reclutamiento.app.api_proceso.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoComiteDocument;

@Repository
public interface ProcesoComiteMongoRepository extends MongoRepository<ProcesoComiteDocument, String> {
}
