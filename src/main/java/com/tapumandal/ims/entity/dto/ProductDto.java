package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.Measurement;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProductDto {

    @Nullable
    private String id;

    @NotNull(message = "Product name can't be null")
    @Size(min=2, max = 50, message = "Write a proper product name")
    private String name;

    @NotEmpty
    @Size(min=2, max = 50, message = "Product must have a price")
    private String pricePerUnit;

    @Valid
    @Nullable
    Set<MeasurementDto> measurement = new HashSet<MeasurementDto>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Set<MeasurementDto> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Set<MeasurementDto> measurement) {
        this.measurement = measurement;
    }
}

