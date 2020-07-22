package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.Delivery;
import com.tapumandal.ims.entity.dto.DeliveryDto;
import com.tapumandal.ims.repository.DeliveryRepository;
import com.tapumandal.ims.service.ChallanManagementService;
import com.tapumandal.ims.service.DeliveryService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private Delivery delivery;

    public DeliveryServiceImpl(){}

    public DeliveryServiceImpl(Delivery delivery){
        this.delivery = delivery;
    }

    @Override
    public Delivery create(DeliveryDto deliveryDto) {
        System.out.println("DTO:");
        System.out.println(new Gson().toJson(deliveryDto));
        Delivery pro = new Delivery(deliveryDto);
        System.out.println("XXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXX");
        System.out.println("ENTITY:");
        System.out.println(new Gson().toJson(pro));
//        if(!checkUsers(pro)){
//            System.out.println("CHECK USER");
//            return null;
//        }
//
//        if(!checkVehicle(pro)){
//            System.out.println("CHECK VEHICLE");
//            return null;
//        }


        Optional<Delivery> delivery;

//        try{
            int receiveChallanId = deliveryRepository.create(pro);
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

    private boolean checkVehicle(Delivery delivery) {
//        if(delivery.getVehicle() != null){
//            return vehicleService.isActive(delivery.getVehicle().getId());
//        }
        return true;
    }

    private boolean checkUsers(Delivery delivery) {

//        if(delivery.getDsr() != null){
//            System.out.println("getDsr NOT NULL");
//            if(!userService.isActive(delivery.getDsr().getId()))
//                return false;
//        }
//        if(delivery.getDriver() != null){
//            System.out.println("getDriver NOT NULL");
//            if(!userService.isActive(delivery.getDriver().getId()))
//                return false;
//        }
//        if(delivery.getHelpingHand() != null){
//            System.out.println("getHelpingHand NOT NULL");
//            if(!userService.isActive(delivery.getHelpingHand().getId()))
//                return false;
//        }
        return true;
    }

    @Override
    public Delivery update(DeliveryDto deliveryDto) {


        Delivery pro = new Delivery(deliveryDto);

        Optional<Delivery> delivery;
//        try{
            int proId = deliveryRepository.update(pro);
            delivery = Optional.ofNullable(deliveryRepository.getById(proId));
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
