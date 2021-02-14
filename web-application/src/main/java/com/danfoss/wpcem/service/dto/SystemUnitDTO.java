package com.danfoss.wpcem.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SystemUnit entity.
 */
public class SystemUnitDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private Set<SystemDeviceDTO> systemDevices = new HashSet<>();

    private Set<UserDTO> users = new HashSet<>();

    public Set<SystemDeviceDTO> getSystemDevices() {
        return systemDevices;
    }

    public void setSystemDevices(Set<SystemDeviceDTO> systemDevices) {
        this.systemDevices = systemDevices;
    }

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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemUnitDTO systemUnitDTO = (SystemUnitDTO) o;
        if (systemUnitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemUnitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemUnitDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
