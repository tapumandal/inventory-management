package com.tapumandal.ims.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ims.annotation.CustomMeasurementSerializer;
import com.tapumandal.ims.entity.dto.ChallanProductDto;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.entity.dto.ProductDto;
import com.tapumandal.ims.entity.dto.ReceiveChallanDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challan")
public class ReceiveChallan {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;



    @OneToOne
    private Supplier supplier;

//    Challan Delivered Unit Details
    @Column(name = "challan_delivered_vehicle")
    private String challanDeliveredVehicle;

    @Column(name = "challan_delivered_person")
    private String challanDeliveredPerson;

    @Column(name = "challan_delivered_contact")
    private String challanDeliveredContact;

    @Column(name = "challan_revceived_time")
    private String challanRevceivedTime;


    @Column(name = "total_product_cost")
    private int totalProductCost;

    @Column(name = "vat")
    private int vat;

    @Column(name = "tax")
    private int tax;

    @Column(name = "other_cost")
    private int otherCost;

    @Column(name = "payable")
    private int payable;

    @Column(name = "total_paid")
    private int totalPaid;

    @Column(name = "due")
    private int due;

    @OneToMany(mappedBy = "receiveChallan")
    private List<ChallanProduct> challanProducts;


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

    public ReceiveChallan() {}
    public ReceiveChallan(ReceiveChallanDto receiveChallanDto) {
        this.id  = receiveChallanDto.getId();
        this.challanDeliveredVehicle  = receiveChallanDto.getChallan_delivered_vehicle();
        this.challanDeliveredPerson  = receiveChallanDto.getChallan_delivered_person();
        this.challanDeliveredContact  = receiveChallanDto.getChallan_delivered_contact();
        this.challanRevceivedTime  = receiveChallanDto.getChallan_revceived_time();
        this.totalProductCost  = receiveChallanDto.getTotal_product_cost();
        this.vat  = receiveChallanDto.getVat();
        this.tax  = receiveChallanDto.getTax();
        this.otherCost  = receiveChallanDto.getOther_cost();
        this.payable  = receiveChallanDto.getPayable();
        this.totalPaid  = receiveChallanDto.getTotal_paid();
        this.due  = receiveChallanDto.getDue();

        Supplier supplierTmp = new Supplier();
        supplierTmp.setId(receiveChallanDto.getSupplier_id());
        this.supplier  = supplierTmp;

        List<ChallanProduct> challanProductTmp = new ArrayList<ChallanProduct>();
        for (ChallanProductDto cpDtoTmp: receiveChallanDto.getChallan_products()){
            challanProductTmp.add(new ChallanProduct(cpDtoTmp));
        }

        this.challanProducts  = challanProductTmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getChallanDeliveredVehicle() {
        return challanDeliveredVehicle;
    }

    public void setChallanDeliveredVehicle(String challanDeliveredVehicle) {
        this.challanDeliveredVehicle = challanDeliveredVehicle;
    }

    public String getChallanDeliveredPerson() {
        return challanDeliveredPerson;
    }

    public void setChallanDeliveredPerson(String challanDeliveredPerson) {
        this.challanDeliveredPerson = challanDeliveredPerson;
    }

    public String getChallanDeliveredContact() {
        return challanDeliveredContact;
    }

    public void setChallanDeliveredContact(String challanDeliveredContact) {
        this.challanDeliveredContact = challanDeliveredContact;
    }

    public String getChallanRevceivedTime() {
        return challanRevceivedTime;
    }

    public void setChallanRevceivedTime(String challanRevceivedTime) {
        this.challanRevceivedTime = challanRevceivedTime;
    }

    public int getTotalProductCost() {
        return totalProductCost;
    }

    public void setTotalProductCost(int totalProductCost) {
        this.totalProductCost = totalProductCost;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(int otherCost) {
        this.otherCost = otherCost;
    }

    public int getPayable() {
        return payable;
    }

    public void setPayable(int payable) {
        this.payable = payable;
    }

    public int getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(int totalPaid) {
        this.totalPaid = totalPaid;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public List<ChallanProduct> getChallanProducts() {
        return challanProducts;
    }

    public void setChallanProducts(List<ChallanProduct> challanProducts) {
        this.challanProducts = challanProducts;
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
}

