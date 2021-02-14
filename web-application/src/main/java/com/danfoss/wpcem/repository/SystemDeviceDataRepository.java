package com.danfoss.wpcem.repository;

import com.danfoss.wpcem.domain.SystemDeviceData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SystemDeviceData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemDeviceDataRepository extends JpaRepository<SystemDeviceData, Long> {

}
