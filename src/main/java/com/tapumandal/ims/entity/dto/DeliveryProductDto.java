package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class DeliveryProductDto {

    @Nullable
    private int id;

    @NotNull(message = "Select a product")
    private int product_id;

    private int measurement_id;

    private String unit_name;

    private int unit_quantity = 1;

    private String package_name;

    private int unit_per_package = 1;

    private String package_name_level_2;

    private int package_per_package_l2 = 1;

    private int total_unit = 0;

    private int total_package = 0;

    private int total_package_l2 = 0;


    @NotNull(message = "Set Buying price")
    private float buying_price;

    @NotNull(message = "Set selling price")
    private float selling_price;

    private int warehouse_id;

    @NotNull(message = "State must be DELIVERY, RETURNED or WASTAGE")
    private String state;

    public DeliveryProductDto() {
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

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public int getUnit_quantity() {
        return unit_quantity;
    }

    public void setUnit_quantity(int unit_quantity) {
        this.unit_quantity = unit_quantity;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getUnit_per_package() {
        return unit_per_package;
    }

    public void setUnit_per_package(int unit_per_package) {
        this.unit_per_package = unit_per_package;
    }

    public String getPackage_name_level_2() {
        return package_name_level_2;
    }

    public void setPackage_name_level_2(String package_name_level_2) {
        this.package_name_level_2 = package_name_level_2;
    }

    public int getPackage_per_package_l2() {
        return package_per_package_l2;
    }

    public void setPackage_per_package_l2(int package_per_package_l2) {
        this.package_per_package_l2 = package_per_package_l2;
    }

    public int getTotal_unit() {
        return total_unit;
    }

    public void setTotal_unit(int total_unit) {
        this.total_unit = total_unit;
    }

    public int getTotal_package() {
        return total_package;
    }

    public void setTotal_package(int total_package) {
        this.total_package = total_package;
    }

    public int getTotal_package_l2() {
        return total_package_l2;
    }

    public void setTotal_package_l2(int total_package_l2) {
        this.total_package_l2 = total_package_l2;
    }

    public float getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(float buying_price) {
        this.buying_price = buying_price;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

