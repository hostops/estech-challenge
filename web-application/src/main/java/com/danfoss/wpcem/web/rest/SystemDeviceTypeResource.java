package com.danfoss.wpcem.web.rest;
import com.danfoss.wpcem.service.SystemDeviceTypeService;
import com.danfoss.wpcem.web.rest.errors.BadRequestAlertException;
import com.danfoss.wpcem.web.rest.util.HeaderUtil;
import com.danfoss.wpcem.web.rest.util.PaginationUtil;
import com.danfoss.wpcem.service.dto.SystemDeviceTypeDTO;
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
 * REST controller for managing SystemDeviceType.
 */
@RestController
@RequestMapping("/api")
public class SystemDeviceTypeResource {

    private final Logger log = LoggerFactory.getLogger(SystemDeviceTypeResource.class);

    private static final String ENTITY_NAME = "systemDeviceType";

    private final SystemDeviceTypeService systemDeviceTypeService;

    public SystemDeviceTypeResource(SystemDeviceTypeService systemDeviceTypeService) {
        this.systemDeviceTypeService = systemDeviceTypeService;
    }

    /**
     * POST  /system-device-types : Create a new systemDeviceType.
     *
     * @param systemDeviceTypeDTO the systemDeviceTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemDeviceTypeDTO, or with status 400 (Bad Request) if the systemDeviceType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-device-types")
    public ResponseEntity<SystemDeviceTypeDTO> createSystemDeviceType(@Valid @RequestBody SystemDeviceTypeDTO systemDeviceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save SystemDeviceType : {}", systemDeviceTypeDTO);
        if (systemDeviceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemDeviceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemDeviceTypeDTO result = systemDeviceTypeService.save(systemDeviceTypeDTO);
        return ResponseEntity.created(new URI("/api/system-device-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-device-types : Updates an existing systemDeviceType.
     *
     * @param systemDeviceTypeDTO the systemDeviceTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemDeviceTypeDTO,
     * or with status 400 (Bad Request) if the systemDeviceTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the systemDeviceTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-device-types")
    public ResponseEntity<SystemDeviceTypeDTO> updateSystemDeviceType(@Valid @RequestBody SystemDeviceTypeDTO systemDeviceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update SystemDeviceType : {}", systemDeviceTypeDTO);
        if (systemDeviceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemDeviceTypeDTO result = systemDeviceTypeService.save(systemDeviceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemDeviceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-device-types : get all the systemDeviceTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of systemDeviceTypes in body
     */
    @GetMapping("/system-device-types")
    public ResponseEntity<List<SystemDeviceTypeDTO>> getAllSystemDeviceTypes(Pageable pageable) {
        log.debug("REST request to get a page of SystemDeviceTypes");
        Page<SystemDeviceTypeDTO> page = systemDeviceTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/system-device-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /system-device-types/:id : get the "id" systemDeviceType.
     *
     * @param id the id of the systemDeviceTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemDeviceTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/system-device-types/{id}")
    public ResponseEntity<SystemDeviceTypeDTO> getSystemDeviceType(@PathVariable Long id) {
        log.debug("REST request to get SystemDeviceType : {}", id);
        Optional<SystemDeviceTypeDTO> systemDeviceTypeDTO = systemDeviceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemDeviceTypeDTO);
    }

    /**
     * DELETE  /system-device-types/:id : delete the "id" systemDeviceType.
     *
     * @param id the id of the systemDeviceTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-device-types/{id}")
    public ResponseEntity<Void> deleteSystemDeviceType(@PathVariable Long id) {
        log.debug("REST request to delete SystemDeviceType : {}", id);
        systemDeviceTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
