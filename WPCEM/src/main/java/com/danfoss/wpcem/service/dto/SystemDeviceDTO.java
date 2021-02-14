package com.danfoss.wpcem.service.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SystemDevice entity.
 */
public class SystemDeviceDTO implements Serializable {

    private Long id;

    private String serialNumber;

    private String deviceConfiguration;

    private Set<SystemDeviceDataDTO> data = new HashSet<>();

    private Long typeId;

    private String typeName;

    private Set<SystemDeviceDTO> connectionsUpcomings = new HashSet<>();

    private Long systemUnitId;

    private String systemUnitName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeviceConfiguration() {
        return deviceConfiguration;
    }

    public void setDeviceConfiguration(String deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long systemDeviceTypeId) {
        this.typeId = systemDeviceTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String systemDeviceTypeName) {
        this.typeName = systemDeviceTypeName;
    }

    public Set<SystemDeviceDTO> getConnectionsUpcomings() {
        return connectionsUpcomings;
    }

    public void setConnectionsUpcomings(Set<SystemDeviceDTO> systemDevices) {
        this.connectionsUpcomings = systemDevices;
    }

    public Long getSystemUnitId() {
        return systemUnitId;
    }

    public void setSystemUnitId(Long systemUnitId) {
        this.systemUnitId = systemUnitId;
    }

    public String getSystemUnitName() {
        return systemUnitName;
    }

    public void setSystemUnitName(String systemUnitName) {
        this.systemUnitName = systemUnitName;
    }

    public Set<SystemDeviceDataDTO> getData() {
        return data;
    }

    public void setData(Set<SystemDeviceDataDTO> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemDeviceDTO systemDeviceDTO = (SystemDeviceDTO) o;
        if (systemDeviceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDeviceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDeviceDTO{" +
            "id=" + getId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", deviceConfiguration='" + getDeviceConfiguration() + "'" +
            ", type=" + getTypeId() +
            ", type='" + getTypeName() + "'" +
            ", systemUnit=" + getSystemUnitId() +
            ", systemUnit='" + getSystemUnitName() + "'" +
            "}";
    }
}
