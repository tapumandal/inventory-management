package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.Product;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;

@Component
public class MeasurementDto {

    @NotNull
    private int id;

    private String unitName;

    private String packageName;

    private String unitPerPackage;

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
}
