package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.ChallanProductDto;
import com.tapumandal.ims.util.ApplicationPreferences;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "challan_product")
public class ChallanProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @Column(name = "buying_price")
    private int buyingPrice;

    @Column(name = "selling_price")
    private int sellingPrice;

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


//{"id":0,
//        "supplier":{
//                    "id":39,
//                    "companyId":1,
//                    "isActive":true,
//                    "isDeleted":false
//                    },
//        "challanDeliveredVehicle":"challan_delivered_vehicle",
//        "challanDeliveredPerson":"challan_delivered_person",
//        "challanDeliveredContact":"challan_delivered_contact",
//        "challanRevceivedTime":"challan_revceived_time",
//        "totalProductCost":200,
//        "vat":2,
//        "tax":2,
//        "otherCost":0,
//        "payable":204,
//        "totalPaid":204,
//        "due":0,
//        "challanProducts":
//                        [
//                            {
//                                "id":0,
//                                "product":
//                                            {
//                                                "id":37,
//                                                "companyId":1,
//                                                "isActive":true,
//                                                "isDeleted":false,
//                                                "measurement":[]
//                                            },
//                                "measurement":{
//                                                "id":1,
//                                                "companyId":1,
//                                                "isActive":true,
//                                                "isDeleted":false,
//                                                "products":[]
//                                              },
//                                "buyingPrice":100,
//                                "sellingPrice":110,
//                                "warehouse":
//                                            {
//                                                "id":41,
//                                                "companyId":1,
//                                                "isActive":true,
//                                                "isDeleted":false
//                                            },
//                                "companyId":1,
//                                "isDeleted":false
//                            }
//                        ],
//        "companyId":1,
//        "isActive":true,
//        "isDeleted":false
//}