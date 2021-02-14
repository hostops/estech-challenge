package com.danfoss.wpcem.web.rest;

import com.danfoss.wpcem.WpcemApp;

import com.danfoss.wpcem.domain.SystemDeviceType;
import com.danfoss.wpcem.repository.SystemDeviceTypeRepository;
import com.danfoss.wpcem.service.SystemDeviceTypeService;
import com.danfoss.wpcem.service.dto.SystemDeviceTypeDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceTypeMapper;
import com.danfoss.wpcem.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.danfoss.wpcem.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.danfoss.wpcem.domain.enumeration.DeviceType;
/**
 * Test class for the SystemDeviceTypeResource REST controller.
 *
 * @see SystemDeviceTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpcemApp.class)
public class SystemDeviceTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final DeviceType DEFAULT_DEVICE_TYPE = DeviceType.CONTROLLER;
    private static final DeviceType UPDATED_DEVICE_TYPE = DeviceType.CONFIGURABLE;

    private static final String DEFAULT_DATA_SHEET = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SHEET = "BBBBBBBBBB";

    @Autowired
    private SystemDeviceTypeRepository systemDeviceTypeRepository;

    @Autowired
    private SystemDeviceTypeMapper systemDeviceTypeMapper;

    @Autowired
    private SystemDeviceTypeService systemDeviceTypeService;

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

    private MockMvc restSystemDeviceTypeMockMvc;

    private SystemDeviceType systemDeviceType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemDeviceTypeResource systemDeviceTypeResource = new SystemDeviceTypeResource(systemDeviceTypeService);
        this.restSystemDeviceTypeMockMvc = MockMvcBuilders.standaloneSetup(systemDeviceTypeResource)
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
    public static SystemDeviceType createEntity(EntityManager em) {
        SystemDeviceType systemDeviceType = new SystemDeviceType()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .dataSheet(DEFAULT_DATA_SHEET);
        return systemDeviceType;
    }

    @Before
    public void initTest() {
        systemDeviceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemDeviceType() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceTypeRepository.findAll().size();

        // Create the SystemDeviceType
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(systemDeviceType);
        restSystemDeviceTypeMockMvc.perform(post("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the SystemDeviceType in the database
        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SystemDeviceType testSystemDeviceType = systemDeviceTypeList.get(systemDeviceTypeList.size() - 1);
        assertThat(testSystemDeviceType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemDeviceType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSystemDeviceType.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testSystemDeviceType.getDataSheet()).isEqualTo(DEFAULT_DATA_SHEET);
    }

    @Test
    @Transactional
    public void createSystemDeviceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceTypeRepository.findAll().size();

        // Create the SystemDeviceType with an existing ID
        systemDeviceType.setId(1L);
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(systemDeviceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemDeviceTypeMockMvc.perform(post("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDeviceType in the database
        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemDeviceTypeRepository.findAll().size();
        // set the field null
        systemDeviceType.setName(null);

        // Create the SystemDeviceType, which fails.
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(systemDeviceType);

        restSystemDeviceTypeMockMvc.perform(post("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isBadRequest());

        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemDeviceTypeRepository.findAll().size();
        // set the field null
        systemDeviceType.setDescription(null);

        // Create the SystemDeviceType, which fails.
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(systemDeviceType);

        restSystemDeviceTypeMockMvc.perform(post("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isBadRequest());

        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemDeviceTypes() throws Exception {
        // Initialize the database
        systemDeviceTypeRepository.saveAndFlush(systemDeviceType);

        // Get all the systemDeviceTypeList
        restSystemDeviceTypeMockMvc.perform(get("/api/system-device-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemDeviceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dataSheet").value(hasItem(DEFAULT_DATA_SHEET.toString())));
    }
    
    @Test
    @Transactional
    public void getSystemDeviceType() throws Exception {
        // Initialize the database
        systemDeviceTypeRepository.saveAndFlush(systemDeviceType);

        // Get the systemDeviceType
        restSystemDeviceTypeMockMvc.perform(get("/api/system-device-types/{id}", systemDeviceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemDeviceType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE.toString()))
            .andExpect(jsonPath("$.dataSheet").value(DEFAULT_DATA_SHEET.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSystemDeviceType() throws Exception {
        // Get the systemDeviceType
        restSystemDeviceTypeMockMvc.perform(get("/api/system-device-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemDeviceType() throws Exception {
        // Initialize the database
        systemDeviceTypeRepository.saveAndFlush(systemDeviceType);

        int databaseSizeBeforeUpdate = systemDeviceTypeRepository.findAll().size();

        // Update the systemDeviceType
        SystemDeviceType updatedSystemDeviceType = systemDeviceTypeRepository.findById(systemDeviceType.getId()).get();
        // Disconnect from session so that the updates on updatedSystemDeviceType are not directly saved in db
        em.detach(updatedSystemDeviceType);
        updatedSystemDeviceType
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .deviceType(UPDATED_DEVICE_TYPE)
            .dataSheet(UPDATED_DATA_SHEET);
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(updatedSystemDeviceType);

        restSystemDeviceTypeMockMvc.perform(put("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the SystemDeviceType in the database
        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeUpdate);
        SystemDeviceType testSystemDeviceType = systemDeviceTypeList.get(systemDeviceTypeList.size() - 1);
        assertThat(testSystemDeviceType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemDeviceType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSystemDeviceType.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testSystemDeviceType.getDataSheet()).isEqualTo(UPDATED_DATA_SHEET);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemDeviceType() throws Exception {
        int databaseSizeBeforeUpdate = systemDeviceTypeRepository.findAll().size();

        // Create the SystemDeviceType
        SystemDeviceTypeDTO systemDeviceTypeDTO = systemDeviceTypeMapper.toDto(systemDeviceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemDeviceTypeMockMvc.perform(put("/api/system-device-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDeviceType in the database
        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemDeviceType() throws Exception {
        // Initialize the database
        systemDeviceTypeRepository.saveAndFlush(systemDeviceType);

        int databaseSizeBeforeDelete = systemDeviceTypeRepository.findAll().size();

        // Delete the systemDeviceType
        restSystemDeviceTypeMockMvc.perform(delete("/api/system-device-types/{id}", systemDeviceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemDeviceType> systemDeviceTypeList = systemDeviceTypeRepository.findAll();
        assertThat(systemDeviceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDeviceType.class);
        SystemDeviceType systemDeviceType1 = new SystemDeviceType();
        systemDeviceType1.setId(1L);
        SystemDeviceType systemDeviceType2 = new SystemDeviceType();
        systemDeviceType2.setId(systemDeviceType1.getId());
        assertThat(systemDeviceType1).isEqualTo(systemDeviceType2);
        systemDeviceType2.setId(2L);
        assertThat(systemDeviceType1).isNotEqualTo(systemDeviceType2);
        systemDeviceType1.setId(null);
        assertThat(systemDeviceType1).isNotEqualTo(systemDeviceType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDeviceTypeDTO.class);
        SystemDeviceTypeDTO systemDeviceTypeDTO1 = new SystemDeviceTypeDTO();
        systemDeviceTypeDTO1.setId(1L);
        SystemDeviceTypeDTO systemDeviceTypeDTO2 = new SystemDeviceTypeDTO();
        assertThat(systemDeviceTypeDTO1).isNotEqualTo(systemDeviceTypeDTO2);
        systemDeviceTypeDTO2.setId(systemDeviceTypeDTO1.getId());
        assertThat(systemDeviceTypeDTO1).isEqualTo(systemDeviceTypeDTO2);
        systemDeviceTypeDTO2.setId(2L);
        assertThat(systemDeviceTypeDTO1).isNotEqualTo(systemDeviceTypeDTO2);
        systemDeviceTypeDTO1.setId(null);
        assertThat(systemDeviceTypeDTO1).isNotEqualTo(systemDeviceTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(systemDeviceTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(systemDeviceTypeMapper.fromId(null)).isNull();
    }
}
