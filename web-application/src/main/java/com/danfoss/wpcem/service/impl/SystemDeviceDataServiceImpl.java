package com.danfoss.wpcem.service.impl;

import com.danfoss.wpcem.service.SystemDeviceDataService;
import com.danfoss.wpcem.domain.SystemDeviceData;
import com.danfoss.wpcem.repository.SystemDeviceDataRepository;
import com.danfoss.wpcem.service.dto.SystemDeviceDataDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SystemDeviceData.
 */
@Service
@Transactional
public class SystemDeviceDataServiceImpl implements SystemDeviceDataService {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceDataServiceImpl.class);

    private final SystemDeviceDataRepository systemDeviceDataRepository;

    private final SystemDeviceDataMapper systemDeviceDataMapper;

    public SystemDeviceDataServiceImpl(SystemDeviceDataRepository systemDeviceDataRepository, SystemDeviceDataMapper systemDeviceDataMapper) {
        this.systemDeviceDataRepository = systemDeviceDataRepository;
        this.systemDeviceDataMapper = systemDeviceDataMapper;
    }

    /**
     * Save a systemDeviceData.
     *
     * @param systemDeviceDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SystemDeviceDataDTO save(SystemDeviceDataDTO systemDeviceDataDTO) {
        log.debug("Request to save SystemDeviceData : {}", systemDeviceDataDTO);
        SystemDeviceData systemDeviceData = systemDeviceDataMapper.toEntity(systemDeviceDataDTO);
        systemDeviceData = systemDeviceDataRepository.save(systemDeviceData);
        return systemDeviceDataMapper.toDto(systemDeviceData);
    }

    /**
     * Get all the systemDeviceData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SystemDeviceDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SystemDeviceData");
        return systemDeviceDataRepository.findAll(pageable)
            .map(systemDeviceDataMapper::toDto);
    }


    /**
     * Get one systemDeviceData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemDeviceDataDTO> findOne(Long id) {
        log.debug("Request to get SystemDeviceData : {}", id);
        return systemDeviceDataRepository.findById(id)
            .map(systemDeviceDataMapper::toDto);
    }

    /**
     * Delete the systemDeviceData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemDeviceData : {}", id);
        systemDeviceDataRepository.deleteById(id);
    }
}
