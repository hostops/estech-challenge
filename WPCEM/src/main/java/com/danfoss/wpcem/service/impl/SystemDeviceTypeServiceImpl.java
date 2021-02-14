package com.danfoss.wpcem.service.impl;

import com.danfoss.wpcem.service.SystemDeviceTypeService;
import com.danfoss.wpcem.domain.SystemDeviceType;
import com.danfoss.wpcem.repository.SystemDeviceTypeRepository;
import com.danfoss.wpcem.service.dto.SystemDeviceTypeDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SystemDeviceType.
 */
@Service
@Transactional
public class SystemDeviceTypeServiceImpl implements SystemDeviceTypeService {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceTypeServiceImpl.class);

    private final SystemDeviceTypeRepository systemDeviceTypeRepository;

    private final SystemDeviceTypeMapper systemDeviceTypeMapper;

    public SystemDeviceTypeServiceImpl(SystemDeviceTypeRepository systemDeviceTypeRepository, SystemDeviceTypeMapper systemDeviceTypeMapper) {
        this.systemDeviceTypeRepository = systemDeviceTypeRepository;
        this.systemDeviceTypeMapper = systemDeviceTypeMapper;
    }

    /**
     * Save a systemDeviceType.
     *
     * @param systemDeviceTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SystemDeviceTypeDTO save(SystemDeviceTypeDTO systemDeviceTypeDTO) {
        log.debug("Request to save SystemDeviceType : {}", systemDeviceTypeDTO);
        SystemDeviceType systemDeviceType = systemDeviceTypeMapper.toEntity(systemDeviceTypeDTO);
        systemDeviceType = systemDeviceTypeRepository.save(systemDeviceType);
        return systemDeviceTypeMapper.toDto(systemDeviceType);
    }

    /**
     * Get all the systemDeviceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SystemDeviceTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SystemDeviceTypes");
        return systemDeviceTypeRepository.findAll(pageable)
            .map(systemDeviceTypeMapper::toDto);
    }


    /**
     * Get one systemDeviceType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemDeviceTypeDTO> findOne(Long id) {
        log.debug("Request to get SystemDeviceType : {}", id);
        return systemDeviceTypeRepository.findById(id)
            .map(systemDeviceTypeMapper::toDto);
    }

    /**
     * Delete the systemDeviceType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemDeviceType : {}", id);
        systemDeviceTypeRepository.deleteById(id);
    }
}
