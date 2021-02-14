package com.danfoss.wpcem.service;

import com.danfoss.wpcem.service.dto.SystemUnitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SystemUnit.
 */
public interface SystemUnitService {

    /**
     * Save a systemUnit.
     *
     * @param systemUnitDTO the entity to save
     * @return the persisted entity
     */
    SystemUnitDTO save(SystemUnitDTO systemUnitDTO);

    /**
     * Get all the systemUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SystemUnitDTO> findAll(Pageable pageable);

    /**
     * Get all the SystemUnit with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SystemUnitDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" systemUnit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SystemUnitDTO> findOne(Long id);

    /**
     * Delete the "id" systemUnit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
