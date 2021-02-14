package com.danfoss.wpcem.service;

import com.danfoss.wpcem.service.dto.SystemDeviceTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SystemDeviceType.
 */
public interface SystemDeviceTypeService {

    /**
     * Save a systemDeviceType.
     *
     * @param systemDeviceTypeDTO the entity to save
     * @return the persisted entity
     */
    SystemDeviceTypeDTO save(SystemDeviceTypeDTO systemDeviceTypeDTO);

    /**
     * Get all the systemDeviceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SystemDeviceTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" systemDeviceType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SystemDeviceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" systemDeviceType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
