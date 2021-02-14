package com.danfoss.wpcem.web.rest;

import com.danfoss.wpcem.WpcemApp;

import com.danfoss.wpcem.domain.SystemUnit;
import com.danfoss.wpcem.repository.SystemUnitRepository;
import com.danfoss.wpcem.service.SystemUnitService;
import com.danfoss.wpcem.service.dto.SystemUnitDTO;
import com.danfoss.wpcem.service.mapper.SystemUnitMapper;
import com.danfoss.wpcem.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.danfoss.wpcem.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SystemUnitResource REST controller.
 *
 * @see SystemUnitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpcemApp.class)
public class SystemUnitResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SystemUnitRepository systemUnitRepository;

    @Mock
    private SystemUnitRepository systemUnitRepositoryMock;

    @Autowired
    private SystemUnitMapper systemUnitMapper;

    @Mock
    private SystemUnitService systemUnitServiceMock;

    @Autowired
    private SystemUnitService systemUnitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSystemUnitMockMvc;

    private SystemUnit systemUnit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemUnitResource systemUnitResource = new SystemUnitResource(systemUnitService);
        this.restSystemUnitMockMvc = MockMvcBuilders.standaloneSetup(systemUnitResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemUnit createEntity(EntityManager em) {
        SystemUnit systemUnit = new SystemUnit()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return systemUnit;
    }

    @Before
    public void initTest() {
        systemUnit = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemUnit() throws Exception {
        int databaseSizeBeforeCreate = systemUnitRepository.findAll().size();

        // Create the SystemUnit
        SystemUnitDTO systemUnitDTO = systemUnitMapper.toDto(systemUnit);
        restSystemUnitMockMvc.perform(post("/api/system-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemUnitDTO)))
            .andExpect(status().isCreated());

        // Validate the SystemUnit in the database
        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeCreate + 1);
        SystemUnit testSystemUnit = systemUnitList.get(systemUnitList.size() - 1);
        assertThat(testSystemUnit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemUnit.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSystemUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemUnitRepository.findAll().size();

        // Create the SystemUnit with an existing ID
        systemUnit.setId(1L);
        SystemUnitDTO systemUnitDTO = systemUnitMapper.toDto(systemUnit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemUnitMockMvc.perform(post("/api/system-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemUnit in the database
        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemUnitRepository.findAll().size();
        // set the field null
        systemUnit.setName(null);

        // Create the SystemUnit, which fails.
        SystemUnitDTO systemUnitDTO = systemUnitMapper.toDto(systemUnit);

        restSystemUnitMockMvc.perform(post("/api/system-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemUnitDTO)))
            .andExpect(status().isBadRequest());

        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemUnits() throws Exception {
        // Initialize the database
        systemUnitRepository.saveAndFlush(systemUnit);

        // Get all the systemUnitList
        restSystemUnitMockMvc.perform(get("/api/system-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSystemUnitsWithEagerRelationshipsIsEnabled() throws Exception {
        SystemUnitResource systemUnitResource = new SystemUnitResource(systemUnitServiceMock);
        when(systemUnitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSystemUnitMockMvc = MockMvcBuilders.standaloneSetup(systemUnitResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSystemUnitMockMvc.perform(get("/api/system-units?eagerload=true"))
        .andExpect(status().isOk());

        verify(systemUnitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSystemUnitsWithEagerRelationshipsIsNotEnabled() throws Exception {
        SystemUnitResource systemUnitResource = new SystemUnitResource(systemUnitServiceMock);
            when(systemUnitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSystemUnitMockMvc = MockMvcBuilders.standaloneSetup(systemUnitResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSystemUnitMockMvc.perform(get("/api/system-units?eagerload=true"))
        .andExpect(status().isOk());

            verify(systemUnitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSystemUnit() throws Exception {
        // Initialize the database
        systemUnitRepository.saveAndFlush(systemUnit);

        // Get the systemUnit
        restSystemUnitMockMvc.perform(get("/api/system-units/{id}", systemUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemUnit.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSystemUnit() throws Exception {
        // Get the systemUnit
        restSystemUnitMockMvc.perform(get("/api/system-units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemUnit() throws Exception {
        // Initialize the database
        systemUnitRepository.saveAndFlush(systemUnit);

        int databaseSizeBeforeUpdate = systemUnitRepository.findAll().size();

        // Update the systemUnit
        SystemUnit updatedSystemUnit = systemUnitRepository.findById(systemUnit.getId()).get();
        // Disconnect from session so that the updates on updatedSystemUnit are not directly saved in db
        em.detach(updatedSystemUnit);
        updatedSystemUnit
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        SystemUnitDTO systemUnitDTO = systemUnitMapper.toDto(updatedSystemUnit);

        restSystemUnitMockMvc.perform(put("/api/system-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemUnitDTO)))
            .andExpect(status().isOk());

        // Validate the SystemUnit in the database
        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeUpdate);
        SystemUnit testSystemUnit = systemUnitList.get(systemUnitList.size() - 1);
        assertThat(testSystemUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemUnit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemUnit() throws Exception {
        int databaseSizeBeforeUpdate = systemUnitRepository.findAll().size();

        // Create the SystemUnit
        SystemUnitDTO systemUnitDTO = systemUnitMapper.toDto(systemUnit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemUnitMockMvc.perform(put("/api/system-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemUnit in the database
        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemUnit() throws Exception {
        // Initialize the database
        systemUnitRepository.saveAndFlush(systemUnit);

        int databaseSizeBeforeDelete = systemUnitRepository.findAll().size();

        // Delete the systemUnit
        restSystemUnitMockMvc.perform(delete("/api/system-units/{id}", systemUnit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemUnit> systemUnitList = systemUnitRepository.findAll();
        assertThat(systemUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemUnit.class);
        SystemUnit systemUnit1 = new SystemUnit();
        systemUnit1.setId(1L);
        SystemUnit systemUnit2 = new SystemUnit();
        systemUnit2.setId(systemUnit1.getId());
        assertThat(systemUnit1).isEqualTo(systemUnit2);
        systemUnit2.setId(2L);
        assertThat(systemUnit1).isNotEqualTo(systemUnit2);
        systemUnit1.setId(null);
        assertThat(systemUnit1).isNotEqualTo(systemUnit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemUnitDTO.class);
        SystemUnitDTO systemUnitDTO1 = new SystemUnitDTO();
        systemUnitDTO1.setId(1L);
        SystemUnitDTO systemUnitDTO2 = new SystemUnitDTO();
        assertThat(systemUnitDTO1).isNotEqualTo(systemUnitDTO2);
        systemUnitDTO2.setId(systemUnitDTO1.getId());
        assertThat(systemUnitDTO1).isEqualTo(systemUnitDTO2);
        systemUnitDTO2.setId(2L);
        assertThat(systemUnitDTO1).isNotEqualTo(systemUnitDTO2);
        systemUnitDTO1.setId(null);
        assertThat(systemUnitDTO1).isNotEqualTo(systemUnitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(systemUnitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(systemUnitMapper.fromId(null)).isNull();
    }
}
