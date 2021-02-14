package com.danfoss.wpcem.repository;

import com.danfoss.wpcem.domain.SystemDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SystemDevice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemDeviceRepository extends JpaRepository<SystemDevice, Long> {

    @Query(value = "select distinct system_device from SystemDevice system_device left join fetch system_device.connectionsUpcomings",
        countQuery = "select count(distinct system_device) from SystemDevice system_device")
    Page<SystemDevice> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct system_device from SystemDevice system_device left join fetch system_device.connectionsUpcomings")
    List<SystemDevice> findAllWithEagerRelationships();

    @Query("select system_device from SystemDevice system_device left join fetch system_device.connectionsUpcomings where system_device.id =:id")
    Optional<SystemDevice> findOneWithEagerRelationships(@Param("id") Long id);

}
