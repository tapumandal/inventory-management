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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private List<ReturnedProduct> returnedProducts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private List<WastageProduct> wastageProducts;

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

        User dsr = new User();
        if(deliveryDto.getDsr_id() != 0) {
            dsr.setId(deliveryDto.getDsr_id());
        }else{
            dsr = new User();
        }

        Vehicle vehicle = new Vehicle();
        if(deliveryDto.getVehicle_id() != 0) {
            vehicle.setId(deliveryDto.getVehicle_id());
        }else{
            vehicle = null;
        }

        User driver = new User();
        if(deliveryDto.getDriver_id() != 0) {
            driver.setId(deliveryDto.getDriver_id());
        }else{
            driver = null;
        }

        User helpingHand = new User();
        if(deliveryDto.getHelping_hand_id() != 0 ){
            helpingHand.setId(deliveryDto.getHelping_hand_id());
        }else {
            helpingHand = null;
        }

        this.deliveryTime = deliveryDto.getDelivery_time();
        this.dsr = dsr;
        this.vehicle = vehicle;
        this.driver = driver;
        this.helpingHand = helpingHand;

        if(!deliveryDto.getDelivery_products().isEmpty()) {
            List<DeliveryProduct> tmp = new ArrayList<DeliveryProduct>();
            for (DeliveryProductDto dpDto : deliveryDto.getDelivery_products()) {
                tmp.add(new DeliveryProduct(dpDto));
            }
            this.deliveryProducts = tmp;
        }

        if(!deliveryDto.getReturned_products().isEmpty()) {
            List<ReturnedProduct> tmp = new ArrayList<ReturnedProduct>();
            for (DeliveryProductDto dpDto : deliveryDto.getReturned_products()) {
                tmp.add(new ReturnedProduct(dpDto));
            }
            this.returnedProducts = tmp;
        }

        if(!deliveryDto.getWastage_products().isEmpty()) {
            List<WastageProduct> tmp = new ArrayList<WastageProduct>();
            for (DeliveryProductDto dpDto : deliveryDto.getWastage_products()) {
                tmp.add(new WastageProduct(dpDto));
            }
            this.wastageProducts = tmp;
        }
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

    public List<ReturnedProduct> getReturnedProducts() {
        return returnedProducts;
    }

    public void setReturnedProducts(List<ReturnedProduct> returnedProducts) {
        this.returnedProducts = returnedProducts;
    }

    public List<WastageProduct> getWastageProducts() {
        return wastageProducts;
    }

    public void setWastageProducts(List<WastageProduct> wastageProducts) {
        this.wastageProducts = wastageProducts;
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