package reclutamiento.app.api_proceso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;

import java.util.UUID;

public interface ProcesoComiteRepository extends JpaRepository<ProcesoComiteEntity, UUID> {
}
