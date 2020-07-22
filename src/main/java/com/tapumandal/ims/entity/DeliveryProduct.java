package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.ChallanProductDto;
import com.tapumandal.ims.entity.dto.DeliveryProductDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "delivery_product")
public class DeliveryProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;


    private String unitName;
    private int unitQuantity = 1;
    private String packageName;
    private int unitPerPackage = 1;
    private String packageNameLevel2;
    private int packagePerPackageL2 = 1;
    private int totalUnit = 0;
    private int totalPackage = 0;
    private int totalPackageL2 = 0;



    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @Column(name = "buying_price")
    private float buyingPrice;

    @Column(name = "selling_price")
    private float sellingPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="warehouse_id", nullable=true)
    private Warehouse warehouse;


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

    private String state;


    public DeliveryProduct() {}

    public DeliveryProduct(DeliveryProductDto dpDto) {

        this.id = dpDto.getId();
        Product proTmp = new Product();
        proTmp.setId(dpDto.getProduct_id());
        this.product = proTmp;
        Measurement mesTmp = new Measurement();
        mesTmp.setId(dpDto.getMeasurement_id());
        this.measurement = mesTmp;
        this.unitName =  dpDto.getUnit_name();
        this.unitQuantity =  dpDto.getUnit_quantity();
        this.packageName =  dpDto.getPackage_name();
        this.unitPerPackage =  dpDto.getUnit_per_package();
        this.packageNameLevel2 =  dpDto.getPackage_name_level_2();
        this.packagePerPackageL2 =  dpDto.getPackage_per_package_l2();
        this.totalUnit =  dpDto.getTotal_unit();
        this.totalPackage =  dpDto.getTotal_package();
        this.totalPackageL2 =  dpDto.getTotal_package_l2();
        this.buyingPrice = dpDto.getBuying_price();
        this.sellingPrice = dpDto.getSelling_price();
        Warehouse warhTmp = new Warehouse();
        warhTmp.setId(dpDto.getWarehouse_id());
        this.warehouse = warhTmp;
        this.state = dpDto.getState();
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


    public String getUnitName() {
        return unitName;
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
        return packageName;
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
        return packageNameLevel2;
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

    public int getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(int totalUnit) {
        this.totalUnit = totalUnit;
    }

    public int getTotalPackage() {
        return totalPackage;
    }

    public void setTotalPackage(int totalPackage) {
        this.totalPackage = totalPackage;
    }

    public int getTotalPackageL2() {
        return totalPackageL2;
    }

    public void setTotalPackageL2(int totalPackageL2) {
        this.totalPackageL2 = totalPackageL2;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}