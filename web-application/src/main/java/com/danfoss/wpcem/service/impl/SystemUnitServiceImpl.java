package com.danfoss.wpcem.service.impl;

import com.danfoss.wpcem.service.SystemUnitService;
import com.danfoss.wpcem.domain.SystemUnit;
import com.danfoss.wpcem.repository.SystemUnitRepository;
import com.danfoss.wpcem.service.dto.SystemUnitDTO;
import com.danfoss.wpcem.service.mapper.SystemUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SystemUnit.
 */
@Service
@Transactional
public class SystemUnitServiceImpl implements SystemUnitService {

    private final Logger log = LoggerFactory.getLogger(SystemUnitServiceImpl.class);

    private final SystemUnitRepository systemUnitRepository;

    private final SystemUnitMapper systemUnitMapper;

    public SystemUnitServiceImpl(SystemUnitRepository systemUnitRepository, SystemUnitMapper systemUnitMapper) {
        this.systemUnitRepository = systemUnitRepository;
        this.systemUnitMapper = systemUnitMapper;
    }

    /**
     * Save a systemUnit.
     *
     * @param systemUnitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SystemUnitDTO save(SystemUnitDTO systemUnitDTO) {
        log.debug("Request to save SystemUnit : {}", systemUnitDTO);
        SystemUnit systemUnit = systemUnitMapper.toEntity(systemUnitDTO);
        systemUnit = systemUnitRepository.save(systemUnit);
        return systemUnitMapper.toDto(systemUnit);
    }

    /**
     * Get all the systemUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SystemUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SystemUnits");
        return systemUnitRepository.findAll(pageable)
            .map(systemUnitMapper::toDto);
    }

    /**
     * Get all the SystemUnit with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SystemUnitDTO> findAllWithEagerRelationships(Pageable pageable) {
        return systemUnitRepository.findAllWithEagerRelationships(pageable).map(systemUnitMapper::toDto);
    }
    

    /**
     * Get one systemUnit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemUnitDTO> findOne(Long id) {
        log.debug("Request to get SystemUnit : {}", id);
        return systemUnitRepository.findOneWithEagerRelationships(id)
            .map(systemUnitMapper::toDto);
    }

    /**
     * Delete the systemUnit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemUnit : {}", id);
        systemUnitRepository.deleteById(id);
    }
}
