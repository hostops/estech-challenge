package com.danfoss.wpcem.service.impl;

import com.danfoss.wpcem.service.SystemDeviceService;
import com.danfoss.wpcem.domain.SystemDevice;
import com.danfoss.wpcem.repository.SystemDeviceRepository;
import com.danfoss.wpcem.service.dto.SystemDeviceDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SystemDevice.
 */
@Service
@Transactional
public class SystemDeviceServiceImpl implements SystemDeviceService {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceServiceImpl.class);

    private final SystemDeviceRepository systemDeviceRepository;

    private final SystemDeviceMapper systemDeviceMapper;

    public SystemDeviceServiceImpl(SystemDeviceRepository systemDeviceRepository, SystemDeviceMapper systemDeviceMapper) {
        this.systemDeviceRepository = systemDeviceRepository;
        this.systemDeviceMapper = systemDeviceMapper;
    }

    /**
     * Save a systemDevice.
     *
     * @param systemDeviceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SystemDeviceDTO save(SystemDeviceDTO systemDeviceDTO) {
        log.debug("Request to save SystemDevice : {}", systemDeviceDTO);
        SystemDevice systemDevice = systemDeviceMapper.toEntity(systemDeviceDTO);
        systemDevice = systemDeviceRepository.save(systemDevice);
        return systemDeviceMapper.toDto(systemDevice);
    }

    /**
     * Get all the systemDevices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SystemDeviceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SystemDevices");
        return systemDeviceRepository.findAll(pageable)
            .map(systemDeviceMapper::toDto);
    }

    /**
     * Get all the SystemDevice with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SystemDeviceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return systemDeviceRepository.findAllWithEagerRelationships(pageable).map(systemDeviceMapper::toDto);
    }
    

    /**
     * Get one systemDevice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemDeviceDTO> findOne(Long id) {
        log.debug("Request to get SystemDevice : {}", id);
        return systemDeviceRepository.findOneWithEagerRelationships(id)
            .map(systemDeviceMapper::toDto);
    }

    /**
     * Delete the systemDevice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemDevice : {}", id);
        systemDeviceRepository.deleteById(id);
    }
}
