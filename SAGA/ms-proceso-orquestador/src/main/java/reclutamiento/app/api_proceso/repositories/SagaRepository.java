package reclutamiento.app.api_proceso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reclutamiento.app.api_proceso.entities.SagaEntity;

import java.util.UUID;

public interface SagaRepository extends JpaRepository<SagaEntity, UUID> {
}