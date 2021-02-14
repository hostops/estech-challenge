package com.danfoss.wpcem.service;

import com.danfoss.wpcem.service.dto.SystemDeviceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SystemDevice.
 */
public interface SystemDeviceService {

    /**
     * Save a systemDevice.
     *
     * @param systemDeviceDTO the entity to save
     * @return the persisted entity
     */
    SystemDeviceDTO save(SystemDeviceDTO systemDeviceDTO);

    /**
     * Get all the systemDevices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SystemDeviceDTO> findAll(Pageable pageable);

    /**
     * Get all the SystemDevice with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SystemDeviceDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" systemDevice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SystemDeviceDTO> findOne(Long id);

    /**
     * Delete the "id" systemDevice.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
