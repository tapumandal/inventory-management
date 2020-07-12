package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.CompanyDto;
import com.tapumandal.ims.entity.dto.VehicleDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vehicle")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "model")
    protected String model;

    @Column(name = "registration")
    protected String registration;

    @Column(name = "chassis")
    protected String chassis;

    @Column(name = "engine")
    protected String engine;

    @Column(name = "company_id", updatable = false)
    protected int companyId = ApplicationPreferences.getUser().getCompany().getId();

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    @OneToOne( mappedBy = "vehicle" )
//    private DeliveryUnit deliveryUnit;

    public Vehicle(){}

    public Vehicle(VehicleDto vehicleDto) {
        this.id = vehicleDto.getId();
        this.name = vehicleDto.getName();
        this.model = vehicleDto.getModel();
        this.registration = vehicleDto.getRegistration();
        this.chassis = vehicleDto.getChassis();
        this.engine = vehicleDto.getEngine();
        this.isActive = vehicleDto.isActive();
        this.isDeleted = vehicleDto.isDeleted();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

//    public DeliveryUnit getDeliveryUnit() {
//        return deliveryUnit;
//    }
//
//    public void setDeliveryUnit(DeliveryUnit deliveryUnit) {
//        this.deliveryUnit = deliveryUnit;
//    }
}
