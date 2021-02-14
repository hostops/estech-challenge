package com.danfoss.wpcem.service.mapper;

import com.danfoss.wpcem.domain.*;
import com.danfoss.wpcem.service.dto.SystemUnitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SystemUnit and its DTO SystemUnitDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, SystemDeviceMapper.class})
public interface SystemUnitMapper extends EntityMapper<SystemUnitDTO, SystemUnit> {


    @Mapping(target = "systemDevices", ignore = true)
    SystemUnit toEntity(SystemUnitDTO systemUnitDTO);

    @Mapping(source = "systemUnit.systemDevices",target = "systemDevices")
    SystemUnitDTO toDto(SystemUnit systemUnit);

    default SystemUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemUnit systemUnit = new SystemUnit();
        systemUnit.setId(id);
        return systemUnit;
    }
}
