package com.danfoss.wpcem.service.mapper;

import com.danfoss.wpcem.domain.*;
import com.danfoss.wpcem.service.dto.SystemDeviceDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SystemDeviceData and its DTO SystemDeviceDataDTO.
 */
@Mapper(componentModel = "spring", uses = {SystemDeviceMapper.class})
public interface SystemDeviceDataMapper extends EntityMapper<SystemDeviceDataDTO, SystemDeviceData> {

    @Mapping(source = "device.id", target = "deviceId")
    @Mapping(source = "device.serialNumber", target = "deviceSerialNumber")
    SystemDeviceDataDTO toDto(SystemDeviceData systemDeviceData);

    @Mapping(source = "deviceId", target = "device")
    SystemDeviceData toEntity(SystemDeviceDataDTO systemDeviceDataDTO);

    default SystemDeviceData fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemDeviceData systemDeviceData = new SystemDeviceData();
        systemDeviceData.setId(id);
        return systemDeviceData;
    }
}
