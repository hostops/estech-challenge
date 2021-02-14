package com.danfoss.wpcem.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.danfoss.wpcem.domain.enumeration.DataType;

/**
 * A DTO for the SystemDeviceData entity.
 */
public class SystemDeviceDataDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant timestamp;

    @NotNull
    private Double dataValue;

    @NotNull
    private DataType dataType;

    private String name;

    private String unit;


    private Long deviceId;

    private String deviceSerialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Double getDataValue() {
        return dataValue;
    }

    public void setDataValue(Double dataValue) {
        this.dataValue = dataValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long systemDeviceId) {
        this.deviceId = systemDeviceId;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String systemDeviceSerialNumber) {
        this.deviceSerialNumber = systemDeviceSerialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemDeviceDataDTO systemDeviceDataDTO = (SystemDeviceDataDTO) o;
        if (systemDeviceDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDeviceDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDeviceDataDTO{" +
            "id=" + getId() +
            ", timestamp='" + getTimestamp() + "'" +
            ", dataValue=" + getDataValue() +
            ", dataType='" + getDataType() + "'" +
            ", name='" + getName() + "'" +
            ", unit='" + getUnit() + "'" +
            ", device=" + getDeviceId() +
            ", device='" + getDeviceSerialNumber() + "'" +
            "}";
    }
}
