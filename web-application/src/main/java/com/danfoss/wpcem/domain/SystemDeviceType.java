package com.danfoss.wpcem.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.danfoss.wpcem.domain.enumeration.DeviceType;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "system_device_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemDeviceType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(name = "data_sheet")
    private String dataSheet;

    @OneToMany(mappedBy = "type")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SystemDevice> devices = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SystemDeviceType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public SystemDeviceType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public SystemDeviceType deviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getDataSheet() {
        return dataSheet;
    }

    public SystemDeviceType dataSheet(String dataSheet) {
        this.dataSheet = dataSheet;
        return this;
    }

    public void setDataSheet(String dataSheet) {
        this.dataSheet = dataSheet;
    }

    public Set<SystemDevice> getDevices() {
        return devices;
    }

    public SystemDeviceType devices(Set<SystemDevice> systemDevices) {
        this.devices = systemDevices;
        return this;
    }

    public SystemDeviceType addDevices(SystemDevice systemDevice) {
        this.devices.add(systemDevice);
        systemDevice.setType(this);
        return this;
    }

    public SystemDeviceType removeDevices(SystemDevice systemDevice) {
        this.devices.remove(systemDevice);
        systemDevice.setType(null);
        return this;
    }

    public void setDevices(Set<SystemDevice> systemDevices) {
        this.devices = systemDevices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SystemDeviceType systemDeviceType = (SystemDeviceType) o;
        if (systemDeviceType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDeviceType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDeviceType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", dataSheet='" + getDataSheet() + "'" +
            "}";
    }
}
