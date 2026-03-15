package reclutamiento.app.api_proceso.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;

@Repository
public interface ProcesoMongoRepository extends MongoRepository<ProcesoDocument, String> {
}
