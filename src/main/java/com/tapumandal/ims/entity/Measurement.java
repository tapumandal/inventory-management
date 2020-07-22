package com.tapumandal.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ims.annotation.CustomProductSerializer;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.entity.dto.ProductDto;
import com.tapumandal.ims.util.ApplicationPreferences;
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

    @Column(name = "unit_quantity")
    private int unitQuantity = 1;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "unit_per_package")
    private int unitPerPackage = 1;

    @Column(name = "package_name_level_2")
    private String packageNameLevel2;

    @Column(name = "package_per_package_l2")
    private int packagePerPackageL2 = 1;

    @Column(name = "company_id", updatable = false)
    protected int companyId = ApplicationPreferences.getUser().getCompany().getId();

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
            this.id = measurementDto.getId();
            this.unitName = measurementDto.getUnit_name();
            this.unitQuantity = measurementDto.getUnit_quantity();
            this.packageName = measurementDto.getPackage_name();
            this.unitPerPackage = measurementDto.getUnit_per_package();
            this.packageNameLevel2 = measurementDto.getPackage_name_level_2();
            this.packagePerPackageL2 = measurementDto.getPackage_per_package_l2();
            this.isActive = measurementDto.isIs_active();
            this.isDeleted = measurementDto.isIs_deleted();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName == null ? "" : unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(int unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getUnitPerPackage() {
        return unitPerPackage;
    }

    public void setUnitPerPackage(int unitPerPackage) {
        this.unitPerPackage = unitPerPackage;
    }

    public String getPackageNameLevel2() {
        return packageNameLevel2 == null ? "" : packageNameLevel2;
    }

    public void setPackageNameLevel2(String packageNameLevel2) {
        this.packageNameLevel2 = packageNameLevel2;
    }

    public int getPackagePerPackageL2() {
        return packagePerPackageL2;
    }

    public void setPackagePerPackageL2(int packagePerPackageL2) {
        this.packagePerPackageL2 = packagePerPackageL2;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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
