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

    @NotEmpty
    @Size(min=2, max = 50, message = "Measurement must have a Unit Name")
    private String unitName;

    @NotEmpty
    @Size(min=2, max = 50, message = "Measurement must have a Unit Name")
    private String packageName;

    @NotEmpty
    @Size(min=2, max = 50, message = "Set the Unit per Package")
    private String unitPerPackage;

    Set<ProductDto> products = new HashSet<ProductDto>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUnitPerPackage() {
        return unitPerPackage;
    }

    public void setUnitPerPackage(String unitPerPackage) {
        this.unitPerPackage = unitPerPackage;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
