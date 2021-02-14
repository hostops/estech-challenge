package com.danfoss.wpcem.service.mapper;

import com.danfoss.wpcem.domain.*;
import com.danfoss.wpcem.service.dto.SystemDeviceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SystemDevice and its DTO SystemDeviceDTO.
 */
@Mapper(componentModel = "spring", uses = {SystemDeviceTypeMapper.class, SystemUnitMapper.class, SystemDeviceDataMapper.class})
public interface SystemDeviceMapper extends EntityMapper<SystemDeviceDTO, SystemDevice> {

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    @Mapping(source = "systemUnit.id", target = "systemUnitId")
    @Mapping(source = "systemUnit.name", target = "systemUnitName")
    @Mapping(source = "systemDevice.data", target = "data")
    SystemDeviceDTO toDto(SystemDevice systemDevice);

    @Mapping(target = "data", ignore = true)
    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "systemUnitId", target = "systemUnit")
    @Mapping(target = "connectionsOutgoings", ignore = true)
    SystemDevice toEntity(SystemDeviceDTO systemDeviceDTO);

    default SystemDevice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemDevice systemDevice = new SystemDevice();
        systemDevice.setId(id);
        return systemDevice;
    }
}
