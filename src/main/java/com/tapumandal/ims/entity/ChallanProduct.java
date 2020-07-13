package com.tapumandal.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ims.annotation.CustomMeasurementSerializer;
import com.tapumandal.ims.entity.dto.ChallanProductDto;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.entity.dto.ProductDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challan_product")
public class ChallanProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;



    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @Column(name = "buying_price")
    private int buyingPrice;

    @Column(name = "selling_price")
    private int sellingPrice;

    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=true)
    private Warehouse warehouse;


    @ManyToOne
    @JoinColumn(name = "receive_challan_id", nullable = false)
    @JsonIgnore
    private ReceiveChallan receiveChallan;



    @Column(name = "company_id", updatable = false)
    protected int companyId = ApplicationPreferences.getUser().getCompany().getId();

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;



    public ChallanProduct() {}

    public ChallanProduct(ChallanProductDto cpDtoTmp) {
        this.id = cpDtoTmp.getId();
        this.buyingPrice = cpDtoTmp.getBuying_price();
        this.sellingPrice = cpDtoTmp.getSelling_price();

        Product proTmp = new Product();
        proTmp.setId(cpDtoTmp.getProduct_id());
        this.product = proTmp;

        Measurement mesTmp = new Measurement();
        mesTmp.setId(cpDtoTmp.getMeasurement_id());
        this.measurement = mesTmp;

        Warehouse warhTmp = new Warehouse();
        warhTmp.setId(cpDtoTmp.getWarehouse_id());
        this.warehouse = warhTmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public ReceiveChallan getReceiveChallan() {
        return receiveChallan;
    }

    public void setReceiveChallan(ReceiveChallan receiveChallan) {
        this.receiveChallan = receiveChallan;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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
}

