package reclutamiento.app.api_proceso.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProcesoRepository extends JpaRepository<ProcesoEntity, UUID> {

    @Query("select c "
            + " from ProcesoEntity c "
            + " where lower(c.nombre) like lower(concat('%', :filtro, '%'))"
            + " or lower(c.resumen) like lower(concat('%', :filtro, '%'))")
    Page<ProcesoEntity> getProcesoByPage(
            String filtro, Pageable pageable
    );

}
