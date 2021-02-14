package com.danfoss.wpcem.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SystemUnit.
 */
@Entity
@Table(name = "system_unit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "systemUnit", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONE)
    private Set<SystemDevice> systemDevices = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "system_unit_users",
               joinColumns = @JoinColumn(name = "system_unit_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

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

    public SystemUnit name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public SystemUnit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SystemDevice> getSystemDevices() {
        return systemDevices;
    }

    public SystemUnit systemDevices(Set<SystemDevice> systemDevices) {
        this.systemDevices = systemDevices;
        return this;
    }

    public SystemUnit addController(SystemDevice systemDevice) {
        this.systemDevices.add(systemDevice);
        systemDevice.setSystemUnit(this);
        return this;
    }

    public SystemUnit removeController(SystemDevice systemDevice) {
        this.systemDevices.remove(systemDevice);
        systemDevice.setSystemUnit(null);
        return this;
    }

    public void setSystemDevices(Set<SystemDevice> systemDevices) {
        this.systemDevices = systemDevices;
    }

    public Set<User> getUsers() {
        return users;
    }

    public SystemUnit users(Set<User> users) {
        this.users = users;
        return this;
    }

    public SystemUnit addUsers(User user) {
        this.users.add(user);
        return this;
    }

    public SystemUnit removeUsers(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
        SystemUnit systemUnit = (SystemUnit) o;
        if (systemUnit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemUnit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemUnit{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
