package com.tapumandal.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tapumandal.ims.entity.dto.ChallanDto;
import com.tapumandal.ims.entity.dto.ChallanProductDto;
import com.tapumandal.ims.entity.dto.DeliveryDto;
import com.tapumandal.ims.entity.dto.DeliveryProductDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dsr_id")
    @JsonIgnoreProperties(value = "company")
    private User dsr;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "driver_id")
    @JsonIgnoreProperties(value = "company")
    private User  driver;

    @ManyToOne(optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "helping_hand_id")
    @JsonIgnoreProperties(value = "company")
    private User helpingHand;


    @Column(name = "delivery_time")
    private String deliveryTime;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private List<DeliveryProduct> deliveryProducts;

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

    public Delivery() {}
    public Delivery(DeliveryDto deliveryDto) {


        this.id = deliveryDto.getId();
        User tmpDsr = new User();
        tmpDsr.setId(deliveryDto.getDsr_id());
        this.dsr = tmpDsr;

        Vehicle tmpVlc = new Vehicle();
        tmpVlc.setId(deliveryDto.getVehicle_id());
        this.vehicle = tmpVlc;

        User tmpDriver = new User();
        tmpDriver.setId(deliveryDto.getDriver_id());
        this.driver = tmpDriver;

        User tmpHelpingHand = new User();
        tmpHelpingHand.setId(deliveryDto.getHelping_hand_id());
        this.helpingHand = tmpHelpingHand;

        this.deliveryTime = deliveryDto.getDelivery_time();

        List<DeliveryProduct> deliveryProductsTmp = new ArrayList<DeliveryProduct>();
        for (DeliveryProductDto dpDto: deliveryDto.getDelivery_products()){
            deliveryProductsTmp.add(new DeliveryProduct(dpDto));
        }
        this.deliveryProducts  = deliveryProductsTmp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDsr() {
        return dsr;
    }

    public void setDsr(User dsr) {
        this.dsr = dsr;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public User getHelpingHand() {
        return helpingHand;
    }

    public void setHelpingHand(User helpingHand) {
        this.helpingHand = helpingHand;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<DeliveryProduct> getDeliveryProducts() {
        return deliveryProducts;
    }

    public void setDeliveryProducts(List<DeliveryProduct> deliveryProducts) {
        this.deliveryProducts = deliveryProducts;
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


//{
//        "id":0,
//        "dsr":
//            {"id":4,
//            "isEmailVerified":false,
//            "isActive":true,
//            "isDeleted":false},
//        "vehicle":
//            {"id":1,
//            "companyId":1,
//            "isActive":true,
//            "isDeleted":false},
//        "driver":
//            {"id":5,
//            "isEmailVerified":false,
//            "isActive":true,
//            "isDeleted":false},
//        "helpingHand":
//            {"id":6,
//            "isEmailVerified":false,
//            "isActive":true,
//            "isDeleted":false},
//        "deliveryTime":"1",
//        "deliveryProducts":[
//                {
//                    "id":0,
//                    "product":
//                        {
//                            "id":1,
//                            "companyId":1,
//                            "isActive":true,
//                            "isDeleted":false,
//                            "measurement":[]
//                        },
//                    "unitName":"unit_name",
//                    "unitQuantity":1,
//                    "packageName":"package_name",
//                    "unitPerPackage":10,
//                    "packageNameLevel2":"package_name_level_2",
//                    "packagePerPackageL2":5,
//                    "totalUnit":0,
//                    "totalPackage":2,
//                    "totalPackageL2":0,
//                    "measurement":{
//                        "id":1,
//                        "unitQuantity":1,
//                        "unitPerPackage":1,
//                        "packagePerPackageL2":1,
//                        "companyId":1,
//                        "isActive":true,
//                        "isDeleted":false,
//                        "products":[]
//                    },
//                    "buyingPrice":100.5,
//                    "sellingPrice":112.0,
//                    "warehouse":
//                        {
//                            "id":1,
//                            "companyId":1,
//                            "isActive":true,
//                            "isDeleted":false
//                        },
//                    "companyId":1,
//                    "isDeleted":false,
//                    "state":"DELIVERY"
//                }],
//        "companyId":1,
//        "isDeleted":false
//}