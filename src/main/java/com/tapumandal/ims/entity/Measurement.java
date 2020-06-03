package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.MeasurementDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "measurement")
@Component
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

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "measurement")
//    Set<Product> products = new HashSet<Product>();

//    @Where(clause = "products_isDeleted == false")

    public Measurement() {}

    public Measurement(MeasurementDto measurementDto) {
        this.setId(measurementDto.getId());
        this.setUnitName(measurementDto.getUnitName());
        this.setPackageName(measurementDto.getPackageName());
        this.setUnitPerPackage(measurementDto.getUnitPerPackage());
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

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
}
