package com.danfoss.wpcem.web.rest;
import com.danfoss.wpcem.service.SystemUnitService;
import com.danfoss.wpcem.web.rest.errors.BadRequestAlertException;
import com.danfoss.wpcem.web.rest.util.HeaderUtil;
import com.danfoss.wpcem.web.rest.util.PaginationUtil;
import com.danfoss.wpcem.service.dto.SystemUnitDTO;
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
 * REST controller for managing SystemUnit.
 */
@RestController
@RequestMapping("/api")
public class SystemUnitResource {

    private final Logger log = LoggerFactory.getLogger(SystemUnitResource.class);

    private static final String ENTITY_NAME = "systemUnit";

    private final SystemUnitService systemUnitService;

    public SystemUnitResource(SystemUnitService systemUnitService) {
        this.systemUnitService = systemUnitService;
    }

    /**
     * POST  /system-units : Create a new systemUnit.
     *
     * @param systemUnitDTO the systemUnitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemUnitDTO, or with status 400 (Bad Request) if the systemUnit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-units")
    public ResponseEntity<SystemUnitDTO> createSystemUnit(@Valid @RequestBody SystemUnitDTO systemUnitDTO) throws URISyntaxException {
        log.debug("REST request to save SystemUnit : {}", systemUnitDTO);
        if (systemUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemUnitDTO result = systemUnitService.save(systemUnitDTO);
        return ResponseEntity.created(new URI("/api/system-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-units : Updates an existing systemUnit.
     *
     * @param systemUnitDTO the systemUnitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemUnitDTO,
     * or with status 400 (Bad Request) if the systemUnitDTO is not valid,
     * or with status 500 (Internal Server Error) if the systemUnitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-units")
    public ResponseEntity<SystemUnitDTO> updateSystemUnit(@Valid @RequestBody SystemUnitDTO systemUnitDTO) throws URISyntaxException {
        log.debug("REST request to update SystemUnit : {}", systemUnitDTO);
        if (systemUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemUnitDTO result = systemUnitService.save(systemUnitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-units : get all the systemUnits.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of systemUnits in body
     */
    @GetMapping("/system-units")
    public ResponseEntity<List<SystemUnitDTO>> getAllSystemUnits(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of SystemUnits");
        Page<SystemUnitDTO> page;
        if (eagerload) {
            page = systemUnitService.findAllWithEagerRelationships(pageable);
        } else {
            page = systemUnitService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/system-units?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /system-units/:id : get the "id" systemUnit.
     *
     * @param id the id of the systemUnitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemUnitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/system-units/{id}")
    public ResponseEntity<SystemUnitDTO> getSystemUnit(@PathVariable Long id) {
        log.debug("REST request to get SystemUnit : {}", id);
        Optional<SystemUnitDTO> systemUnitDTO = systemUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemUnitDTO);
    }

    /**
     * DELETE  /system-units/:id : delete the "id" systemUnit.
     *
     * @param id the id of the systemUnitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-units/{id}")
    public ResponseEntity<Void> deleteSystemUnit(@PathVariable Long id) {
        log.debug("REST request to delete SystemUnit : {}", id);
        systemUnitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
