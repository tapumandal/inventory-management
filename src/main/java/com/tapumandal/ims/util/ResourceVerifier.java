package com.tapumandal.ims.util;

import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceVerifier {

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    ProductService productService;

    @Autowired
    MeasurementService measurementService;

    @Autowired
    WarehouseService warehouseService;



    public boolean checkChallanProduct(List<ChallanProduct> challanProducts) {
        for (ChallanProduct tmpChallanPro: challanProducts) {

            if(!checkProduct(tmpChallanPro.getProduct().getId())){
                System.out.println("checkProduct");
                return false;
            }

            if(!checkMeasurement(tmpChallanPro.getMeasurement().getId())){
                System.out.println("checkMeasurement");
                return false;
            }

            if(!checkWarehouse(tmpChallanPro.getWarehouse().getId())){
                System.out.println("checkWarehouse");
                return false;
            }
        }
        return true;
    }

    public boolean checkProduct(int id) {
        if(!productService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkUser(int id) {
        if(!userService.isActive(id)){
            return false;
        }
        return true;
    }
    public boolean checkVehicle(int id) {
        if(!vehicleService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkSupplier(int id) {
        if(!supplierService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkMeasurement(int id) {
        if(!measurementService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkWarehouse(int id) {
        if(!warehouseService.isActive(id)){
            return false;
        }
        return true;
    }

    public boolean checkMeasurements(List<Measurement> measurements) {

        for (Measurement tmpMeas: measurements) {
            if(!checkMeasurement(tmpMeas.getId())){
                return false;
            }
        }
        return true;
    }

    public boolean checkDeliveryProduct(List<DeliveryProduct> products) {
        if(!products.isEmpty())
        for (DeliveryProduct tmpPro: products) {

            if(!checkProduct(tmpPro.getProduct().getId())){
                System.out.println("checkProduct");
                return false;
            }

            if(!checkMeasurement(tmpPro.getMeasurement().getId())){
                System.out.println("checkMeasurement");
                return false;
            }

            if(!checkWarehouse(tmpPro.getWarehouse().getId())){
                System.out.println("checkWarehouse");
                return false;
            }
        }
        return true;
    }

    public boolean checkReturnProduct(List<ReturnedProduct> products) {
        if(!products.isEmpty())
            for (ReturnedProduct tmpPro: products) {

                if(!checkProduct(tmpPro.getProduct().getId())){
                    System.out.println("checkProduct");
                    return false;
                }

                if(!checkMeasurement(tmpPro.getMeasurement().getId())){
                    System.out.println("checkMeasurement");
                    return false;
                }

                if(!checkWarehouse(tmpPro.getWarehouse().getId())){
                    System.out.println("checkWarehouse");
                    return false;
                }
            }
        return true;
    }

    public boolean checkWastageProduct(List<WastageProduct> products) {
        if(!products.isEmpty())
            for (WastageProduct tmpPro: products) {

                if(!checkProduct(tmpPro.getProduct().getId())){
                    System.out.println("checkProduct");
                    return false;
                }

                if(!checkMeasurement(tmpPro.getMeasurement().getId())){
                    System.out.println("checkMeasurement");
                    return false;
                }

                if(!checkWarehouse(tmpPro.getWarehouse().getId())){
                    System.out.println("checkWarehouse");
                    return false;
                }
            }
        return true;
    }
}


