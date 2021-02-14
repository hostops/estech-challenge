package com.danfoss.wpcem.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SystemDevice.
 */
@Entity
@Table(name = "system_device")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemDevice implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "device_configuration")
    private String deviceConfiguration;

    @OneToMany(mappedBy = "device")
    @Cache(usage = CacheConcurrencyStrategy.NONE)
    private Set<SystemDeviceData> data = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("devices")
    private SystemDeviceType type;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "system_device_connections_upcoming",
               joinColumns = @JoinColumn(name = "system_device_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "connections_upcoming_id", referencedColumnName = "id"))
    private Set<SystemDevice> connectionsUpcomings = new HashSet<>();

    @ManyToOne
    private SystemUnit systemUnit;

    @ManyToMany(mappedBy = "connectionsUpcomings")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<SystemDevice> connectionsOutgoings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public SystemDevice serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeviceConfiguration() {
        return deviceConfiguration;
    }

    public SystemDevice deviceConfiguration(String deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
        return this;
    }

    public void setDeviceConfiguration(String deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
    }

    public Set<SystemDeviceData> getData() {
        return data;
    }

    public SystemDevice data(Set<SystemDeviceData> systemDeviceData) {
        this.data = systemDeviceData;
        return this;
    }

    public SystemDevice addData(SystemDeviceData systemDeviceData) {
        this.data.add(systemDeviceData);
        systemDeviceData.setDevice(this);
        return this;
    }

    public SystemDevice removeData(SystemDeviceData systemDeviceData) {
        this.data.remove(systemDeviceData);
        systemDeviceData.setDevice(null);
        return this;
    }

    public void setData(Set<SystemDeviceData> systemDeviceData) {
        this.data = systemDeviceData;
    }

    public SystemDeviceType getType() {
        return type;
    }

    public SystemDevice type(SystemDeviceType systemDeviceType) {
        this.type = systemDeviceType;
        return this;
    }

    public void setType(SystemDeviceType systemDeviceType) {
        this.type = systemDeviceType;
    }

    public Set<SystemDevice> getConnectionsUpcomings() {
        return connectionsUpcomings;
    }

    public SystemDevice connectionsUpcomings(Set<SystemDevice> systemDevices) {
        this.connectionsUpcomings = systemDevices;
        return this;
    }

    public SystemDevice addConnectionsUpcoming(SystemDevice systemDevice) {
        this.connectionsUpcomings.add(systemDevice);
        systemDevice.getConnectionsOutgoings().add(this);
        return this;
    }

    public SystemDevice removeConnectionsUpcoming(SystemDevice systemDevice) {
        this.connectionsUpcomings.remove(systemDevice);
        systemDevice.getConnectionsOutgoings().remove(this);
        return this;
    }

    public void setConnectionsUpcomings(Set<SystemDevice> systemDevices) {
        this.connectionsUpcomings = systemDevices;
    }

    public SystemUnit getSystemUnit() {
        return systemUnit;
    }

    public SystemDevice systemUnit(SystemUnit systemUnit) {
        this.systemUnit = systemUnit;
        return this;
    }

    public void setSystemUnit(SystemUnit systemUnit) {
        this.systemUnit = systemUnit;
    }

    public Set<SystemDevice> getConnectionsOutgoings() {
        return connectionsOutgoings;
    }

    public SystemDevice connectionsOutgoings(Set<SystemDevice> systemDevices) {
        this.connectionsOutgoings = systemDevices;
        return this;
    }

    public SystemDevice addConnectionsOutgoing(SystemDevice systemDevice) {
        this.connectionsOutgoings.add(systemDevice);
        systemDevice.getConnectionsUpcomings().add(this);
        return this;
    }

    public SystemDevice removeConnectionsOutgoing(SystemDevice systemDevice) {
        this.connectionsOutgoings.remove(systemDevice);
        systemDevice.getConnectionsUpcomings().remove(this);
        return this;
    }

    public void setConnectionsOutgoings(Set<SystemDevice> systemDevices) {
        this.connectionsOutgoings = systemDevices;
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
        SystemDevice systemDevice = (SystemDevice) o;
        if (systemDevice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemDevice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemDevice{" +
            "id=" + getId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", deviceConfiguration='" + getDeviceConfiguration() + "'" +
            "}";
    }
}
