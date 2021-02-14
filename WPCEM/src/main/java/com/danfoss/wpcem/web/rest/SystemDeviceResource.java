package com.danfoss.wpcem.web.rest;
import com.danfoss.wpcem.service.SystemDeviceService;
import com.danfoss.wpcem.web.rest.errors.BadRequestAlertException;
import com.danfoss.wpcem.web.rest.util.HeaderUtil;
import com.danfoss.wpcem.web.rest.util.PaginationUtil;
import com.danfoss.wpcem.service.dto.SystemDeviceDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SystemDevice.
 */
@RestController
@RequestMapping("/api")
public class SystemDeviceResource {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceResource.class);

    private static final String ENTITY_NAME = "systemDevice";

    private final SystemDeviceService systemDeviceService;

    public SystemDeviceResource(SystemDeviceService systemDeviceService) {
        this.systemDeviceService = systemDeviceService;
    }

    /**
     * POST  /system-devices : Create a new systemDevice.
     *
     * @param systemDeviceDTO the systemDeviceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemDeviceDTO, or with status 400 (Bad Request) if the systemDevice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-devices")
    public ResponseEntity<SystemDeviceDTO> createSystemDevice(@RequestBody SystemDeviceDTO systemDeviceDTO) throws URISyntaxException {
        log.debug("REST request to save SystemDevice : {}", systemDeviceDTO);
        if (systemDeviceDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemDevice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemDeviceDTO result = systemDeviceService.save(systemDeviceDTO);
        return ResponseEntity.created(new URI("/api/system-devices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-devices : Updates an existing systemDevice.
     *
     * @param systemDeviceDTO the systemDeviceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemDeviceDTO,
     * or with status 400 (Bad Request) if the systemDeviceDTO is not valid,
     * or with status 500 (Internal Server Error) if the systemDeviceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-devices")
    public ResponseEntity<SystemDeviceDTO> updateSystemDevice(@RequestBody SystemDeviceDTO systemDeviceDTO) throws URISyntaxException {
        log.debug("REST request to update SystemDevice : {}", systemDeviceDTO);
        if (systemDeviceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemDeviceDTO result = systemDeviceService.save(systemDeviceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemDeviceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-devices : get all the systemDevices.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of systemDevices in body
     */
    @GetMapping("/system-devices")
    public ResponseEntity<List<SystemDeviceDTO>> getAllSystemDevices(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of SystemDevices");
        Page<SystemDeviceDTO> page;
        if (eagerload) {
            page = systemDeviceService.findAllWithEagerRelationships(pageable);
        } else {
            page = systemDeviceService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/system-devices?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /system-devices/:id : get the "id" systemDevice.
     *
     * @param id the id of the systemDeviceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemDeviceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/system-devices/{id}")
    public ResponseEntity<SystemDeviceDTO> getSystemDevice(@PathVariable Long id) {
        log.debug("REST request to get SystemDevice : {}", id);
        Optional<SystemDeviceDTO> systemDeviceDTO = systemDeviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemDeviceDTO);
    }

    /**
     * DELETE  /system-devices/:id : delete the "id" systemDevice.
     *
     * @param id the id of the systemDeviceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-devices/{id}")
    public ResponseEntity<Void> deleteSystemDevice(@PathVariable Long id) {
        log.debug("REST request to delete SystemDevice : {}", id);
        systemDeviceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
