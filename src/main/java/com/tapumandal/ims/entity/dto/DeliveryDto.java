package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.Vehicle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class DeliveryDto {

    @Nullable
    private int id;

    @NotNull(message = "Must have a delivery responsible person.")
    private int dsr_id;

    private int vehicle_id;

    private int  driver_id;

    private int helping_hand_id;

    private String delivery_time;

    private List<DeliveryProductDto> delivery_products;
    private List<DeliveryProductDto> returned_products;
    private List<DeliveryProductDto> wastage_products;

    @Nullable
    private boolean is_deleted = false;


    public DeliveryDto() {
    }

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

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public List<DeliveryProductDto> getDelivery_products() {
        return delivery_products;
    }

    public void setDelivery_products(List<DeliveryProductDto> delivery_products) {
        this.delivery_products = delivery_products;
    }

    public List<DeliveryProductDto> getReturned_products() {
        return returned_products;
    }

    public void setReturned_products(List<DeliveryProductDto> returned_products) {
        this.returned_products = returned_products;
    }

    public List<DeliveryProductDto> getWastage_products() {
        return wastage_products;
    }

    public void setWastage_products(List<DeliveryProductDto> wastage_products) {
        this.wastage_products = wastage_products;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}

