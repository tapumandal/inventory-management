package com.tapumandal.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.dto.DeliveryUnitDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "delivery_unit")
public class DeliveryUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;




    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "dsr_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "company")
    private User dsr;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "company")
    private User  driver;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "helping_hand_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "company")
    private User helpingHand;

    @Column(name = "company_id", updatable = false)
    protected int companyId = ApplicationPreferences.getUser().getCompany().getId();

//    @Column(name = "is_active", columnDefinition = "boolean default 1")
//    private boolean isActive = true;
//
//    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
//    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public DeliveryUnit() {}

    public DeliveryUnit(DeliveryUnitDto deliveryUnitDto) {

        User dsr = new User();
        if(deliveryUnitDto.getDsr_id() != 0) {
            dsr.setId(deliveryUnitDto.getDsr_id());
        }else{
            dsr = null;
        }

        Vehicle vehicle = new Vehicle();
        if(deliveryUnitDto.getVehicle_id() != 0) {
            vehicle.setId(deliveryUnitDto.getVehicle_id());
        }else{
            vehicle = null;
        }

        User driver = new User();
        if(deliveryUnitDto.getDriver_id() != 0) {
            driver.setId(deliveryUnitDto.getDriver_id());
        }else{
            driver = null;
        }

        User helpingHand = new User();
        if(deliveryUnitDto.getHelping_hand_id() != 0 ){
            helpingHand.setId(deliveryUnitDto.getHelping_hand_id());
        }else {
            helpingHand = null;
        }


        this.id = deliveryUnitDto.getId();
        this.dsr = dsr;
        this.vehicle = vehicle;
        this.driver = driver;
        this.helpingHand = helpingHand;
//        this.isActive = deliveryUnitDto.isActive();
//        this.isDeleted = deliveryUnitDto.isDeleted();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDsr() {
        return dsr;
    }

    public void setDsr(User dsr) {
        this.dsr = dsr;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public User getHelpingHand() {
        return helpingHand;
    }

    public void setHelpingHand(User helpingHand) {
        this.helpingHand = helpingHand;
    }

//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
//
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        isDeleted = deleted;
//    }

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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
