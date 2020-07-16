package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
public class ChallanProductDto {

    @Nullable
    private int id;

    @NotNull(message = "Select a product")
    private int product_id;

    @NotNull(message = "Select a measurement")
    private int measurement_id;

    @NotNull(message = "Set Buying price")
    private int buying_price;

    @NotNull(message = "Set selling price")
    private int selling_price;

    private int warehouse_id;

    public ChallanProductDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurement_id = measurement_id;
    }

    public int getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(int buying_price) {
        this.buying_price = buying_price;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
}

