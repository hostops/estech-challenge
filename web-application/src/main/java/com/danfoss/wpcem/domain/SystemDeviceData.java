package com.danfoss.wpcem.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.danfoss.wpcem.domain.enumeration.DataType;

/**
 * A SystemDeviceData.
 */
@Entity
@Table(name = "system_device_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemDeviceData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_timestamp", nullable = false)
    private Instant timestamp;

    @NotNull
    @Column(name = "data_value", nullable = false)
    private Double dataValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", nullable = false)
    private DataType dataType;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @ManyToOne
    @JsonIgnoreProperties("data")
    private SystemDevice device;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public SystemDeviceData timestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Double getDataValue() {
        return dataValue;
    }

    public SystemDeviceData dataValue(Double dataValue) {
        this.dataValue = dataValue;
        return this;
    }

    public void setDataValue(Double dataValue) {
        this.dataValue = dataValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    public SystemDeviceData dataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public SystemDeviceData name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public SystemDeviceData unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public SystemDevice getDevice() {
        return device;
    }

    public SystemDeviceData device(SystemDevice systemDevice) {
        this.device = systemDevice;
        return this;
    }

    public void setDevice(SystemDevice systemDevice) {
        this.device = systemDevice;
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
        SystemDeviceData systemDeviceData = (SystemDeviceData) o;
        if (systemDeviceData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDeviceData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDeviceData{" +
            "id=" + getId() +
            ", timestamp='" + getTimestamp() + "'" +
            ", dataValue=" + getDataValue() +
            ", dataType='" + getDataType() + "'" +
            ", name='" + getName() + "'" +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
