package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.CompanyDto;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.entity.dto.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "product")
@DynamicUpdate
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_per_unit")
    private String pricePerUnit;

    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    //    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "product_measurement",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "measurement_id")}
    )
    @Where(clause = "is_deleted = false AND is_active = true" )
    List<Measurement> measurement = new ArrayList<Measurement>();


    public Product(ProductDto productDto) {

        this.setId(productDto.getId());
        this.setName(productDto.getName());
        this.setPricePerUnit(productDto.getPricePerUnit());
        this.setActive(productDto.isActive());

        for(MeasurementDto measurementDto: productDto.getMeasurement()){
            measurement.add(new Measurement(measurementDto));
        }

//        BeanUtils.copyProperties(this.measurement, productDto.getMeasurement());

//        Measurement measurement1 = new Measurement();
//        measurement1.setId(productDto.getMeasurement()..getId());
//        measurement1.setPackageName(productDto.getMeasurement().getPackageName());
//        measurement1.setUnitPerPackage(productDto.getMeasurement().getUnitPerPackage());
//
//        this.name = name;
//        this.pricePerUnit = pricePerUnit;
//        this.measurement.add(measurement1);
    }

    public Product() {

    }

//    public Product(ProductDto productDto) {
//        this.setName(productDto.getName());
//        this.setPricePerUnit(productDto.getPricePerUnit());
//
//
//    }

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

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
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

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }
}

