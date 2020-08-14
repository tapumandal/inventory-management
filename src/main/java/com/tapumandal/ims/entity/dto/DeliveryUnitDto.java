package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeliveryUnitDto {

    @Nullable
    private int id;

    @NotNull(message = "You must have a person to deliver")
    private int dsr_id;

    @Nullable
    private int vehicle_id;

    @Nullable
    private int driver_id;

    @Nullable
    private int helping_hand_id;

    private boolean isActive = true;

    private boolean isDeleted = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDsr_id() {
        return dsr_id;
    }

    public void setDsr_id(int dsr_id) {
        this.dsr_id = dsr_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getHelping_hand_id() {
        return helping_hand_id;
    }

    public void setHelping_hand_id(int helping_hand_id) {
        this.helping_hand_id = helping_hand_id;
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
}