package com.danfoss.wpcem.service;

import com.danfoss.wpcem.service.dto.SystemDeviceDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SystemDeviceData.
 */
public interface SystemDeviceDataService {

    /**
     * Save a systemDeviceData.
     *
     * @param systemDeviceDataDTO the entity to save
     * @return the persisted entity
     */
    SystemDeviceDataDTO save(SystemDeviceDataDTO systemDeviceDataDTO);

    /**
     * Get all the systemDeviceData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SystemDeviceDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" systemDeviceData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SystemDeviceDataDTO> findOne(Long id);

    /**
     * Delete the "id" systemDeviceData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
