package com.danfoss.wpcem.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.danfoss.wpcem.domain.enumeration.DeviceType;

/**
 * A DTO for the SystemDeviceType entity.
 */
@ApiModel(description = "not an ignored comment")
public class SystemDeviceTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private DeviceType deviceType;

    private String dataSheet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getDataSheet() {
        return dataSheet;
    }

    public void setDataSheet(String dataSheet) {
        this.dataSheet = dataSheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemDeviceTypeDTO systemDeviceTypeDTO = (SystemDeviceTypeDTO) o;
        if (systemDeviceTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDeviceTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDeviceTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", dataSheet='" + getDataSheet() + "'" +
            "}";
    }
}
