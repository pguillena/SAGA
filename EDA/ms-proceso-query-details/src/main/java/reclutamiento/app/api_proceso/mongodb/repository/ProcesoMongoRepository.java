package reclutamiento.app.api_proceso.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;

import java.util.List;

@Repository
public interface ProcesoMongoRepository extends MongoRepository<ProcesoDocument, String> {

    @Query(" { $or: [ { nombre: { $regex : ?0, $options: 'i' } }, { resumen: { $regex : ?0, $options: 'i' } } ] } ")
    List<ProcesoDocument> findByLikeNombreOrResumen(String filtro);
}
