package com.tapumandal.ims.entity.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Component
public class MeasurementDto {

    @NotNull
    private int id;

    @NotEmpty(message = "Measurement must have a unit_name")
    private String unit_name;

    @NotNull(message = "Measurement must have a unit_quantity")
    private int unit_quantity = 1;

    private String package_name;

    private int unit_per_package = 1;

    private String package_name_level_2;

    private int package_per_package_l2 = 1;

    private boolean is_active = true;

    private boolean is_deleted = false;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
