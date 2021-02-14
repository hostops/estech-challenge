package com.danfoss.wpcem.web.rest;

import com.danfoss.wpcem.WpcemApp;

import com.danfoss.wpcem.domain.SystemDeviceData;
import com.danfoss.wpcem.repository.SystemDeviceDataRepository;
import com.danfoss.wpcem.service.SystemDeviceDataService;
import com.danfoss.wpcem.service.dto.SystemDeviceDataDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceDataMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.danfoss.wpcem.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.danfoss.wpcem.domain.enumeration.DataType;
/**
 * Test class for the SystemDeviceDataResource REST controller.
 *
 * @see SystemDeviceDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpcemApp.class)
public class SystemDeviceDataResourceIntTest {

    private static final Instant DEFAULT_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_DATA_VALUE = 1D;
    private static final Double UPDATED_DATA_VALUE = 2D;

    private static final DataType DEFAULT_DATA_TYPE = DataType.VIRTUAL;
    private static final DataType UPDATED_DATA_TYPE = DataType.REAL;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    @Autowired
    private SystemDeviceDataRepository systemDeviceDataRepository;

    @Autowired
    private SystemDeviceDataMapper systemDeviceDataMapper;

    @Autowired
    private SystemDeviceDataService systemDeviceDataService;

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

    private MockMvc restSystemDeviceDataMockMvc;

    private SystemDeviceData systemDeviceData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemDeviceDataResource systemDeviceDataResource = new SystemDeviceDataResource(systemDeviceDataService);
        this.restSystemDeviceDataMockMvc = MockMvcBuilders.standaloneSetup(systemDeviceDataResource)
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
    public static SystemDeviceData createEntity(EntityManager em) {
        SystemDeviceData systemDeviceData = new SystemDeviceData()
            .timestamp(DEFAULT_TIMESTAMP)
            .dataValue(DEFAULT_DATA_VALUE)
            .dataType(DEFAULT_DATA_TYPE)
            .name(DEFAULT_NAME)
            .unit(DEFAULT_UNIT);
        return systemDeviceData;
    }

    @Before
    public void initTest() {
        systemDeviceData = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemDeviceData() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceDataRepository.findAll().size();

        // Create the SystemDeviceData
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);
        restSystemDeviceDataMockMvc.perform(post("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isCreated());

        // Validate the SystemDeviceData in the database
        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeCreate + 1);
        SystemDeviceData testSystemDeviceData = systemDeviceDataList.get(systemDeviceDataList.size() - 1);
        assertThat(testSystemDeviceData.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testSystemDeviceData.getDataValue()).isEqualTo(DEFAULT_DATA_VALUE);
        assertThat(testSystemDeviceData.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testSystemDeviceData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemDeviceData.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    public void createSystemDeviceDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceDataRepository.findAll().size();

        // Create the SystemDeviceData with an existing ID
        systemDeviceData.setId(1L);
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemDeviceDataMockMvc.perform(post("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDeviceData in the database
        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemDeviceDataRepository.findAll().size();
        // set the field null
        systemDeviceData.setTimestamp(null);

        // Create the SystemDeviceData, which fails.
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);

        restSystemDeviceDataMockMvc.perform(post("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isBadRequest());

        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemDeviceDataRepository.findAll().size();
        // set the field null
        systemDeviceData.setDataValue(null);

        // Create the SystemDeviceData, which fails.
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);

        restSystemDeviceDataMockMvc.perform(post("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isBadRequest());

        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemDeviceDataRepository.findAll().size();
        // set the field null
        systemDeviceData.setDataType(null);

        // Create the SystemDeviceData, which fails.
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);

        restSystemDeviceDataMockMvc.perform(post("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isBadRequest());

        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemDeviceData() throws Exception {
        // Initialize the database
        systemDeviceDataRepository.saveAndFlush(systemDeviceData);

        // Get all the systemDeviceDataList
        restSystemDeviceDataMockMvc.perform(get("/api/system-device-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemDeviceData.getId().intValue())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dataValue").value(hasItem(DEFAULT_DATA_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getSystemDeviceData() throws Exception {
        // Initialize the database
        systemDeviceDataRepository.saveAndFlush(systemDeviceData);

        // Get the systemDeviceData
        restSystemDeviceDataMockMvc.perform(get("/api/system-device-data/{id}", systemDeviceData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemDeviceData.getId().intValue()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dataValue").value(DEFAULT_DATA_VALUE.doubleValue()))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSystemDeviceData() throws Exception {
        // Get the systemDeviceData
        restSystemDeviceDataMockMvc.perform(get("/api/system-device-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemDeviceData() throws Exception {
        // Initialize the database
        systemDeviceDataRepository.saveAndFlush(systemDeviceData);

        int databaseSizeBeforeUpdate = systemDeviceDataRepository.findAll().size();

        // Update the systemDeviceData
        SystemDeviceData updatedSystemDeviceData = systemDeviceDataRepository.findById(systemDeviceData.getId()).get();
        // Disconnect from session so that the updates on updatedSystemDeviceData are not directly saved in db
        em.detach(updatedSystemDeviceData);
        updatedSystemDeviceData
            .timestamp(UPDATED_TIMESTAMP)
            .dataValue(UPDATED_DATA_VALUE)
            .dataType(UPDATED_DATA_TYPE)
            .name(UPDATED_NAME)
            .unit(UPDATED_UNIT);
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(updatedSystemDeviceData);

        restSystemDeviceDataMockMvc.perform(put("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isOk());

        // Validate the SystemDeviceData in the database
        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeUpdate);
        SystemDeviceData testSystemDeviceData = systemDeviceDataList.get(systemDeviceDataList.size() - 1);
        assertThat(testSystemDeviceData.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testSystemDeviceData.getDataValue()).isEqualTo(UPDATED_DATA_VALUE);
        assertThat(testSystemDeviceData.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testSystemDeviceData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemDeviceData.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemDeviceData() throws Exception {
        int databaseSizeBeforeUpdate = systemDeviceDataRepository.findAll().size();

        // Create the SystemDeviceData
        SystemDeviceDataDTO systemDeviceDataDTO = systemDeviceDataMapper.toDto(systemDeviceData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemDeviceDataMockMvc.perform(put("/api/system-device-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDeviceData in the database
        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemDeviceData() throws Exception {
        // Initialize the database
        systemDeviceDataRepository.saveAndFlush(systemDeviceData);

        int databaseSizeBeforeDelete = systemDeviceDataRepository.findAll().size();

        // Delete the systemDeviceData
        restSystemDeviceDataMockMvc.perform(delete("/api/system-device-data/{id}", systemDeviceData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemDeviceData> systemDeviceDataList = systemDeviceDataRepository.findAll();
        assertThat(systemDeviceDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDeviceData.class);
        SystemDeviceData systemDeviceData1 = new SystemDeviceData();
        systemDeviceData1.setId(1L);
        SystemDeviceData systemDeviceData2 = new SystemDeviceData();
        systemDeviceData2.setId(systemDeviceData1.getId());
        assertThat(systemDeviceData1).isEqualTo(systemDeviceData2);
        systemDeviceData2.setId(2L);
        assertThat(systemDeviceData1).isNotEqualTo(systemDeviceData2);
        systemDeviceData1.setId(null);
        assertThat(systemDeviceData1).isNotEqualTo(systemDeviceData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDeviceDataDTO.class);
        SystemDeviceDataDTO systemDeviceDataDTO1 = new SystemDeviceDataDTO();
        systemDeviceDataDTO1.setId(1L);
        SystemDeviceDataDTO systemDeviceDataDTO2 = new SystemDeviceDataDTO();
        assertThat(systemDeviceDataDTO1).isNotEqualTo(systemDeviceDataDTO2);
        systemDeviceDataDTO2.setId(systemDeviceDataDTO1.getId());
        assertThat(systemDeviceDataDTO1).isEqualTo(systemDeviceDataDTO2);
        systemDeviceDataDTO2.setId(2L);
        assertThat(systemDeviceDataDTO1).isNotEqualTo(systemDeviceDataDTO2);
        systemDeviceDataDTO1.setId(null);
        assertThat(systemDeviceDataDTO1).isNotEqualTo(systemDeviceDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(systemDeviceDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(systemDeviceDataMapper.fromId(null)).isNull();
    }
}
