package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.entity.dto.DeliveryDto;
import com.tapumandal.ims.repository.DeliveryRepository;
import com.tapumandal.ims.service.ChallanManagementService;
import com.tapumandal.ims.service.DeliveryService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.util.MyPagenation;
import com.tapumandal.ims.util.ResourceVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ResourceVerifier resourceVerifier;

    private Delivery delivery;

    public DeliveryServiceImpl(){}

    public DeliveryServiceImpl(Delivery delivery){
        this.delivery = delivery;
    }

    @Override
    public Delivery create(DeliveryDto deliveryDto) {

        System.out.println(new Gson().toJson(deliveryDto));
        Delivery deli = new Delivery(deliveryDto);
        System.out.println(new Gson().toJson(deli));


        if(!resourceVerifier.checkUser(deli.getDsr().getId()) ){
            System.out.println("CHECK DSR");
            return null;
        }

        if(deli.getDriver() != null){
            if(!resourceVerifier.checkUser(deli.getDriver().getId())){
                System.out.println("CHECK Driver");
                return null;
            }
        }

        if(deli.getHelpingHand() != null){
            if(!resourceVerifier.checkUser(deli.getHelpingHand().getId())){
                System.out.println("CHECK Helping Hand");
                return null;
            }
        }

        if(deli.getVehicle() != null){
            if(!resourceVerifier.checkVehicle(deli.getVehicle().getId())){
                System.out.println("CHECK VEHICLE");
                return null;
            }
        }

        if(!resourceVerifier.checkDeliveryProduct(deli.getDeliveryProducts())){
            System.out.println("CHECK getChallanProducts");
            return null;
        }


        if(!resourceVerifier.checkReturnProduct(deli.getReturnedProducts())){
            System.out.println("CHECK getReturnProducts");
            return null;
        }


        if(!resourceVerifier.checkWastageProduct(deli.getWastageProducts())){
            System.out.println("CHECK getWastageProducts");
            return null;
        }

        Optional<Delivery> delivery;

//        try{
            int receiveChallanId = deliveryRepository.create(deli);
        System.out.println("DELIVERY UNIT ID: "+receiveChallanId);
            delivery = Optional.ofNullable(deliveryRepository.getById(receiveChallanId));
//        }catch (Exception e){
//            return null;
//        }

        if(delivery.isPresent()){
            return delivery.get();
        }else{
            return null;
        }
    }


    @Override
    public Delivery update(DeliveryDto deliveryDto) {


        System.out.println(new Gson().toJson(deliveryDto));
        Delivery deli = new Delivery(deliveryDto);
        System.out.println(new Gson().toJson(deli));

        if(!resourceVerifier.checkUser(deli.getDsr().getId()) ){
            System.out.println("CHECK DSR");
            return null;
        }
        System.out.println("checkUser");

        if(deli.getDriver() != null){
            if(!resourceVerifier.checkUser(deli.getDriver().getId())){
                System.out.println("CHECK Driver");
                return null;
            }
        }
        System.out.println("getDriver");

        if(deli.getHelpingHand() != null){
            if(!resourceVerifier.checkUser(deli.getHelpingHand().getId())){
                System.out.println("CHECK Helping Hand");
                return null;
            }
        }
        System.out.println("getHelpingHand");

        if(deli.getVehicle() != null){
            if(!resourceVerifier.checkVehicle(deli.getVehicle().getId())){
                System.out.println("CHECK VEHICLE");
                return null;
            }
        }
        System.out.println("getVehicle");


//        Delivery savedDelivery = new Delivery();
//        if(isActive(deli.getId())){
//            savedDelivery = getById(deli.getId());
//        }else{
//            return null;
//        }

//        savedDelivery = updateNewDelivery(savedDelivery, deli);

        if(!resourceVerifier.checkDeliveryProduct(deli.getDeliveryProducts())){
            System.out.println("CHECK getChallanProducts");
            return null;
        }
//        else{
//            savedDelivery.setDeliveryProducts(updateDeliveryProduct(savedDelivery, deli));
//        }

        if(!resourceVerifier.checkReturnProduct(deli.getReturnedProducts())){
            System.out.println("CHECK getReturnProducts");
            return null;
        }

        if(!resourceVerifier.checkWastageProduct(deli.getWastageProducts())){
            System.out.println("CHECK getWastageProducts");
            return null;
        }




//        Delivery deli = new Delivery(deliveryDto);

        Optional<Delivery> delivery;
//        try{
            int deliId = deliveryRepository.update(deli);
            System.out.println("deliId "+deliId);
            delivery = Optional.ofNullable(deliveryRepository.getById(deliId));
//        System.out.println(new Gson().toJson(delivery.get()));
//        }catch (Exception e){
//            return null;
//        }

        if(delivery.isPresent()){
            return delivery.get();
        }else{
            return null;
        }

    }

    private List<DeliveryProduct> updateDeliveryProduct(Delivery savedDelivery, Delivery deli) {
        List<DeliveryProduct> products = savedDelivery.getDeliveryProducts();
        for (DeliveryProduct tmpPro: products) {

        }

        return products;
    }

    private Delivery updateNewDelivery(Delivery savedDelivery, Delivery deli) {

        savedDelivery.setDsr(deli.getDsr());
        savedDelivery.setVehicle(deli.getVehicle());
        savedDelivery.setDriver(deli.getDriver());
        savedDelivery.setHelpingHand(deli.getHelpingHand());
        savedDelivery.setDeliveryTime(deli.getDeliveryTime());
        savedDelivery.setDeleted(deli.isDeleted());


//        savedDelivery.setDeliveryProducts(deli.getDeliveryProducts());
//        savedDelivery.setReturnedProducts(deli.getReturnedProducts());
//        savedDelivery.setWastageProducts(deli.getWastageProducts());

        return savedDelivery;
    }

    @Override
    public List<Delivery> getAll(Pageable pageable) {
        Optional<List<Delivery>> receiveChallans = Optional.ofNullable(deliveryRepository.getAll(pageable));

        if(receiveChallans.isPresent()){
            return receiveChallans.get();
        }else{
            return null;
        }
    }

    @Override
    public Delivery getById(int id) {

        Optional<Delivery> delivery = Optional.ofNullable(deliveryRepository.getById(id));

        if(delivery.isPresent()){
            return delivery.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return deliveryRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Delivery getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Delivery> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Delivery> delivery = Optional.ofNullable(deliveryRepository.getById(id));
        if(delivery.isPresent()){
            if(delivery.get() != null){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return deliveryRepository.getPageable(pageable);
    }


}
