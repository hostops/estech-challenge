package com.danfoss.wpcem.web.rest;
import com.danfoss.wpcem.service.SystemDeviceDataService;
import com.danfoss.wpcem.web.rest.errors.BadRequestAlertException;
import com.danfoss.wpcem.web.rest.util.HeaderUtil;
import com.danfoss.wpcem.web.rest.util.PaginationUtil;
import com.danfoss.wpcem.service.dto.SystemDeviceDataDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SystemDeviceData.
 */
@RestController
@RequestMapping("/api")
public class SystemDeviceDataResource {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceDataResource.class);

    private static final String ENTITY_NAME = "systemDeviceData";

    private final SystemDeviceDataService systemDeviceDataService;

    public SystemDeviceDataResource(SystemDeviceDataService systemDeviceDataService) {
        this.systemDeviceDataService = systemDeviceDataService;
    }

    /**
     * POST  /system-device-data : Create a new systemDeviceData.
     *
     * @param systemDeviceDataDTO the systemDeviceDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemDeviceDataDTO, or with status 400 (Bad Request) if the systemDeviceData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-device-data")
    public ResponseEntity<SystemDeviceDataDTO> createSystemDeviceData(@Valid @RequestBody SystemDeviceDataDTO systemDeviceDataDTO) throws URISyntaxException {
        log.debug("REST request to save SystemDeviceData : {}", systemDeviceDataDTO);
        if (systemDeviceDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemDeviceData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemDeviceDataDTO result = systemDeviceDataService.save(systemDeviceDataDTO);
        return ResponseEntity.created(new URI("/api/system-device-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-device-data : Updates an existing systemDeviceData.
     *
     * @param systemDeviceDataDTO the systemDeviceDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemDeviceDataDTO,
     * or with status 400 (Bad Request) if the systemDeviceDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the systemDeviceDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-device-data")
    public ResponseEntity<SystemDeviceDataDTO> updateSystemDeviceData(@Valid @RequestBody SystemDeviceDataDTO systemDeviceDataDTO) throws URISyntaxException {
        log.debug("REST request to update SystemDeviceData : {}", systemDeviceDataDTO);
        if (systemDeviceDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemDeviceDataDTO result = systemDeviceDataService.save(systemDeviceDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemDeviceDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-device-data : get all the systemDeviceData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of systemDeviceData in body
     */
    @GetMapping("/system-device-data")
    public ResponseEntity<List<SystemDeviceDataDTO>> getAllSystemDeviceData(Pageable pageable) {
        log.debug("REST request to get a page of SystemDeviceData");
        Page<SystemDeviceDataDTO> page = systemDeviceDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/system-device-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /system-device-data/:id : get the "id" systemDeviceData.
     *
     * @param id the id of the systemDeviceDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemDeviceDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/system-device-data/{id}")
    public ResponseEntity<SystemDeviceDataDTO> getSystemDeviceData(@PathVariable Long id) {
        log.debug("REST request to get SystemDeviceData : {}", id);
        Optional<SystemDeviceDataDTO> systemDeviceDataDTO = systemDeviceDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemDeviceDataDTO);
    }

    /**
     * DELETE  /system-device-data/:id : delete the "id" systemDeviceData.
     *
     * @param id the id of the systemDeviceDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-device-data/{id}")
    public ResponseEntity<Void> deleteSystemDeviceData(@PathVariable Long id) {
        log.debug("REST request to delete SystemDeviceData : {}", id);
        systemDeviceDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
