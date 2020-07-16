package com.tapumandal.ims.entity.dto;

import javax.validation.constraints.NotNull;

import com.sun.istack.Nullable;
import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Component
public class ChallanDto {

    @Nullable
    private int id;

    @Nullable
    private int supplier_id;

    @Nullable
    private String challan_delivered_vehicle;

    @Nullable
    private String challan_delivered_person;

    @Nullable
    private String challan_delivered_contact;

    @Nullable
    private String challan_revceived_time;

    @NotNull(message = "Total product cost can't be empty")
    private int total_product_cost;

    @Nullable
    private int vat;

    @Nullable
    private int tax;

    @Nullable
    private int other_cost;

    @NotNull(message = "Payable can't be empty")
    private int payable;

    @NotNull(message = "Total paid amount can't be empty")
    private int total_paid;

    @Nullable
    private int due;

    @NotNull(message = "Select products")
    private List<ChallanProductDto> challan_products;

    @Nullable
    private boolean is_deleted = false;


    public ChallanDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getChallan_delivered_vehicle() {
        return challan_delivered_vehicle;
    }

    public void setChallan_delivered_vehicle(String challan_delivered_vehicle) {
        this.challan_delivered_vehicle = challan_delivered_vehicle;
    }

    public String getChallan_delivered_person() {
        return challan_delivered_person;
    }

    public void setChallan_delivered_person(String challan_delivered_person) {
        this.challan_delivered_person = challan_delivered_person;
    }

    public String getChallan_delivered_contact() {
        return challan_delivered_contact;
    }

    public void setChallan_delivered_contact(String challan_delivered_contact) {
        this.challan_delivered_contact = challan_delivered_contact;
    }

    public String getChallan_revceived_time() {
        return challan_revceived_time;
    }

    public void setChallan_revceived_time(String challan_revceived_time) {
        this.challan_revceived_time = challan_revceived_time;
    }

    public int getTotal_product_cost() {
        return total_product_cost;
    }

    public void setTotal_product_cost(int total_product_cost) {
        this.total_product_cost = total_product_cost;
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

    public int getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(int other_cost) {
        this.other_cost = other_cost;
    }

    public int getPayable() {
        return payable;
    }

    public void setPayable(int payable) {
        this.payable = payable;
    }

    public int getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(int total_paid) {
        this.total_paid = total_paid;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public List<ChallanProductDto> getChallan_products() {
        return challan_products;
    }

    public void setChallan_products(List<ChallanProductDto> challan_products) {
        this.challan_products = challan_products;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}

