package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.dto.MeasurementDto;
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
    private int id;

    @NotNull(message = "Product name can't be null")
    @Size(min=2, max = 50, message = "Write a proper product name")
    private String name;

    @NotEmpty
    @Size(min=2, max = 50, message = "Product must have a buying price")
    private String buying_price_per_unit;

    @NotEmpty
    @Size(min=2, max = 50, message = "Product must have a selling price")
    private String selling_price_per_unit;

    private boolean active = true;

    Set<MeasurementDto> measurement = new HashSet<MeasurementDto>();

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

    public String getBuying_price_per_unit() {
        return buying_price_per_unit;
    }

    public void setBuying_price_per_unit(String buying_price_per_unit) {
        this.buying_price_per_unit = buying_price_per_unit;
    }

    public String getSelling_price_per_unit() {
        return selling_price_per_unit;
    }

    public void setSelling_price_per_unit(String selling_price_per_unit) {
        this.selling_price_per_unit = selling_price_per_unit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<MeasurementDto> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Set<MeasurementDto> measurement) {
        this.measurement = measurement;
    }
}

