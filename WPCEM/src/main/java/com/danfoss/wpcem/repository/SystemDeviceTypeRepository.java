package com.danfoss.wpcem.repository;

import com.danfoss.wpcem.domain.SystemDeviceType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SystemDeviceType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemDeviceTypeRepository extends JpaRepository<SystemDeviceType, Long> {

}
