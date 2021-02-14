package com.danfoss.wpcem.repository;

import com.danfoss.wpcem.domain.SystemUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SystemUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemUnitRepository extends JpaRepository<SystemUnit, Long> {

    @Query(value = "select distinct system_unit from SystemUnit system_unit left join fetch system_unit.users",
        countQuery = "select count(distinct system_unit) from SystemUnit system_unit")
    Page<SystemUnit> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct system_unit from SystemUnit system_unit left join fetch system_unit.users")
    List<SystemUnit> findAllWithEagerRelationships();

    @Query("select system_unit from SystemUnit system_unit left join fetch system_unit.users where system_unit.id =:id")
    Optional<SystemUnit> findOneWithEagerRelationships(@Param("id") Long id);

}
