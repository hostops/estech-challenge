package com.danfoss.wpcem.service.mapper;

import com.danfoss.wpcem.domain.*;
import com.danfoss.wpcem.service.dto.SystemDeviceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SystemDeviceType and its DTO SystemDeviceTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SystemDeviceTypeMapper extends EntityMapper<SystemDeviceTypeDTO, SystemDeviceType> {


    @Mapping(target = "devices", ignore = true)
    SystemDeviceType toEntity(SystemDeviceTypeDTO systemDeviceTypeDTO);

    default SystemDeviceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemDeviceType systemDeviceType = new SystemDeviceType();
        systemDeviceType.setId(id);
        return systemDeviceType;
    }
}
