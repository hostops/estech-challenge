package com.danfoss.wpcem.web.rest;

import com.danfoss.wpcem.WpcemApp;

import com.danfoss.wpcem.domain.SystemDevice;
import com.danfoss.wpcem.repository.SystemDeviceRepository;
import com.danfoss.wpcem.service.SystemDeviceService;
import com.danfoss.wpcem.service.dto.SystemDeviceDTO;
import com.danfoss.wpcem.service.mapper.SystemDeviceMapper;
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
 * Test class for the SystemDeviceResource REST controller.
 *
 * @see SystemDeviceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpcemApp.class)
public class SystemDeviceResourceIntTest {

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_CONFIGURATION = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_CONFIGURATION = "BBBBBBBBBB";

    @Autowired
    private SystemDeviceRepository systemDeviceRepository;

    @Mock
    private SystemDeviceRepository systemDeviceRepositoryMock;

    @Autowired
    private SystemDeviceMapper systemDeviceMapper;

    @Mock
    private SystemDeviceService systemDeviceServiceMock;

    @Autowired
    private SystemDeviceService systemDeviceService;

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

    private MockMvc restSystemDeviceMockMvc;

    private SystemDevice systemDevice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemDeviceResource systemDeviceResource = new SystemDeviceResource(systemDeviceService);
        this.restSystemDeviceMockMvc = MockMvcBuilders.standaloneSetup(systemDeviceResource)
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
    public static SystemDevice createEntity(EntityManager em) {
        SystemDevice systemDevice = new SystemDevice()
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .deviceConfiguration(DEFAULT_DEVICE_CONFIGURATION);
        return systemDevice;
    }

    @Before
    public void initTest() {
        systemDevice = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemDevice() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceRepository.findAll().size();

        // Create the SystemDevice
        SystemDeviceDTO systemDeviceDTO = systemDeviceMapper.toDto(systemDevice);
        restSystemDeviceMockMvc.perform(post("/api/system-devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDTO)))
            .andExpect(status().isCreated());

        // Validate the SystemDevice in the database
        List<SystemDevice> systemDeviceList = systemDeviceRepository.findAll();
        assertThat(systemDeviceList).hasSize(databaseSizeBeforeCreate + 1);
        SystemDevice testSystemDevice = systemDeviceList.get(systemDeviceList.size() - 1);
        assertThat(testSystemDevice.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testSystemDevice.getDeviceConfiguration()).isEqualTo(DEFAULT_DEVICE_CONFIGURATION);
    }

    @Test
    @Transactional
    public void createSystemDeviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemDeviceRepository.findAll().size();

        // Create the SystemDevice with an existing ID
        systemDevice.setId(1L);
        SystemDeviceDTO systemDeviceDTO = systemDeviceMapper.toDto(systemDevice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemDeviceMockMvc.perform(post("/api/system-devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDevice in the database
        List<SystemDevice> systemDeviceList = systemDeviceRepository.findAll();
        assertThat(systemDeviceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSystemDevices() throws Exception {
        // Initialize the database
        systemDeviceRepository.saveAndFlush(systemDevice);

        // Get all the systemDeviceList
        restSystemDeviceMockMvc.perform(get("/api/system-devices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemDevice.getId().intValue())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].deviceConfiguration").value(hasItem(DEFAULT_DEVICE_CONFIGURATION.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSystemDevicesWithEagerRelationshipsIsEnabled() throws Exception {
        SystemDeviceResource systemDeviceResource = new SystemDeviceResource(systemDeviceServiceMock);
        when(systemDeviceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSystemDeviceMockMvc = MockMvcBuilders.standaloneSetup(systemDeviceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSystemDeviceMockMvc.perform(get("/api/system-devices?eagerload=true"))
        .andExpect(status().isOk());

        verify(systemDeviceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSystemDevicesWithEagerRelationshipsIsNotEnabled() throws Exception {
        SystemDeviceResource systemDeviceResource = new SystemDeviceResource(systemDeviceServiceMock);
            when(systemDeviceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSystemDeviceMockMvc = MockMvcBuilders.standaloneSetup(systemDeviceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSystemDeviceMockMvc.perform(get("/api/system-devices?eagerload=true"))
        .andExpect(status().isOk());

            verify(systemDeviceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSystemDevice() throws Exception {
        // Initialize the database
        systemDeviceRepository.saveAndFlush(systemDevice);

        // Get the systemDevice
        restSystemDeviceMockMvc.perform(get("/api/system-devices/{id}", systemDevice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemDevice.getId().intValue()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.deviceConfiguration").value(DEFAULT_DEVICE_CONFIGURATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSystemDevice() throws Exception {
        // Get the systemDevice
        restSystemDeviceMockMvc.perform(get("/api/system-devices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemDevice() throws Exception {
        // Initialize the database
        systemDeviceRepository.saveAndFlush(systemDevice);

        int databaseSizeBeforeUpdate = systemDeviceRepository.findAll().size();

        // Update the systemDevice
        SystemDevice updatedSystemDevice = systemDeviceRepository.findById(systemDevice.getId()).get();
        // Disconnect from session so that the updates on updatedSystemDevice are not directly saved in db
        em.detach(updatedSystemDevice);
        updatedSystemDevice
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .deviceConfiguration(UPDATED_DEVICE_CONFIGURATION);
        SystemDeviceDTO systemDeviceDTO = systemDeviceMapper.toDto(updatedSystemDevice);

        restSystemDeviceMockMvc.perform(put("/api/system-devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDTO)))
            .andExpect(status().isOk());

        // Validate the SystemDevice in the database
        List<SystemDevice> systemDeviceList = systemDeviceRepository.findAll();
        assertThat(systemDeviceList).hasSize(databaseSizeBeforeUpdate);
        SystemDevice testSystemDevice = systemDeviceList.get(systemDeviceList.size() - 1);
        assertThat(testSystemDevice.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testSystemDevice.getDeviceConfiguration()).isEqualTo(UPDATED_DEVICE_CONFIGURATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemDevice() throws Exception {
        int databaseSizeBeforeUpdate = systemDeviceRepository.findAll().size();

        // Create the SystemDevice
        SystemDeviceDTO systemDeviceDTO = systemDeviceMapper.toDto(systemDevice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemDeviceMockMvc.perform(put("/api/system-devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemDeviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemDevice in the database
        List<SystemDevice> systemDeviceList = systemDeviceRepository.findAll();
        assertThat(systemDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemDevice() throws Exception {
        // Initialize the database
        systemDeviceRepository.saveAndFlush(systemDevice);

        int databaseSizeBeforeDelete = systemDeviceRepository.findAll().size();

        // Delete the systemDevice
        restSystemDeviceMockMvc.perform(delete("/api/system-devices/{id}", systemDevice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemDevice> systemDeviceList = systemDeviceRepository.findAll();
        assertThat(systemDeviceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDevice.class);
        SystemDevice systemDevice1 = new SystemDevice();
        systemDevice1.setId(1L);
        SystemDevice systemDevice2 = new SystemDevice();
        systemDevice2.setId(systemDevice1.getId());
        assertThat(systemDevice1).isEqualTo(systemDevice2);
        systemDevice2.setId(2L);
        assertThat(systemDevice1).isNotEqualTo(systemDevice2);
        systemDevice1.setId(null);
        assertThat(systemDevice1).isNotEqualTo(systemDevice2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemDeviceDTO.class);
        SystemDeviceDTO systemDeviceDTO1 = new SystemDeviceDTO();
        systemDeviceDTO1.setId(1L);
        SystemDeviceDTO systemDeviceDTO2 = new SystemDeviceDTO();
        assertThat(systemDeviceDTO1).isNotEqualTo(systemDeviceDTO2);
        systemDeviceDTO2.setId(systemDeviceDTO1.getId());
        assertThat(systemDeviceDTO1).isEqualTo(systemDeviceDTO2);
        systemDeviceDTO2.setId(2L);
        assertThat(systemDeviceDTO1).isNotEqualTo(systemDeviceDTO2);
        systemDeviceDTO1.setId(null);
        assertThat(systemDeviceDTO1).isNotEqualTo(systemDeviceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(systemDeviceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(systemDeviceMapper.fromId(null)).isNull();
    }
}
