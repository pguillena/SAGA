package reclutamiento.app.api_proceso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reclutamiento.app.api_proceso.entities.OrganoEntity;

import java.util.UUID;

public interface OrganoRepository extends JpaRepository<OrganoEntity, UUID> {
    @Query("select c "
            + " from OrganoEntity c "
            + " where lower(c.nombre) like lower(concat('%', :filtro, '%'))"
            + " or lower(c.sigla) like lower(concat('%', :filtro, '%'))")
    Page<OrganoEntity> getOrganoByPage(
            String filtro, Pageable pageable
    );

}
