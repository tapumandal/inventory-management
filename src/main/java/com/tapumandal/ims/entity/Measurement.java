package com.tapumandal.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ims.annotation.CustomProductSerializer;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.entity.dto.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "measurement")
public class Measurement {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "unit_per_package")
    private String unitPerPackage;

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





//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(
//            name = "product_measurement",
//            joinColumns = {@JoinColumn(name = "measurement_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")}
//    )
//    @Where(clause = "is_deleted = false AND is_active = true" )
//    @JsonSerialize(using = CustomProductSerializer.class)

    @ManyToMany(mappedBy = "measurement", fetch = FetchType.LAZY)
    @Where(clause = "is_deleted = false AND is_active = true" )
    @JsonSerialize(using = CustomProductSerializer.class)
    List<Product> products = new ArrayList<Product>();


    public Measurement() {}

    public Measurement(MeasurementDto measurementDto) {
        this.setId(measurementDto.getId());
        this.setUnitName(measurementDto.getUnitName());
        this.setPackageName(measurementDto.getPackageName());
        this.setUnitPerPackage(measurementDto.getUnitPerPackage());

        for(ProductDto productDto: measurementDto.getProducts()){
            products.add(new Product(productDto));
        }
    }

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

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
