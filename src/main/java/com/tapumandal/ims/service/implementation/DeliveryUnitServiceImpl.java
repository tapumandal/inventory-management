package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.DeliveryUnit;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.Vehicle;
import com.tapumandal.ims.entity.dto.DeliveryUnitDto;
import com.tapumandal.ims.repository.DeliveryUnitRepository;
import com.tapumandal.ims.service.DeliveryUnitService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryUnitServiceImpl implements DeliveryUnitService {

    @Autowired
    DeliveryUnitRepository deliveryUnitRepository;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    private DeliveryUnit deliveryUnit;

    public DeliveryUnitServiceImpl(){}

    public DeliveryUnitServiceImpl(DeliveryUnit deliveryUnit){
        this.deliveryUnit = deliveryUnit;
    }

    @Override
    public DeliveryUnit create(DeliveryUnitDto deliveryUnitDto) {

        DeliveryUnit pro = new DeliveryUnit(deliveryUnitDto);
        System.out.println(new Gson().toJson(pro));
        if(!checkUsers(pro)){
            System.out.println("CHECK USER");
            return null;
        }

        if(!checkVehicle(pro)){
            System.out.println("CHECK VEHICLE");
            return null;
        }

        System.out.println(new Gson().toJson(pro));

        removeUserFromAnotherDeliveryUnit(pro);

        System.out.println("AFTER REMOVE DELIVERY UNIT");

        Optional<DeliveryUnit> deliveryUnit;

//        try{
            int deliveryUnitId = deliveryUnitRepository.create(pro);
        System.out.println("DELIVERY UNIT ID: "+deliveryUnitId);
            deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getById(deliveryUnitId));
//        }catch (Exception e){
//            return null;
//        }

        if(deliveryUnit.isPresent()){
            return deliveryUnit.get();
        }else{
            return null;
        }
    }

    private boolean checkVehicle(DeliveryUnit deliveryUnit) {
        if(deliveryUnit.getVehicle() != null){
            return vehicleService.isActive(deliveryUnit.getVehicle().getId());
        }
        return true;
    }

    private boolean checkUsers(DeliveryUnit deliveryUnit) {

        if(deliveryUnit.getDsr() != null){
            System.out.println("getDsr NOT NULL");
            if(!userService.isActive(deliveryUnit.getDsr().getId()))
                return false;
        }
        if(deliveryUnit.getDriver() != null){
            System.out.println("getDriver NOT NULL");
            if(!userService.isActive(deliveryUnit.getDriver().getId()))
                return false;
        }
        if(deliveryUnit.getHelpingHand() != null){
            System.out.println("getHelpingHand NOT NULL");
            if(!userService.isActive(deliveryUnit.getHelpingHand().getId()))
                return false;
        }
        return true;
    }

    private void removeUserFromAnotherDeliveryUnit(DeliveryUnit pro) {

        if(pro.getDsr() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("dsr_id", String.valueOf(pro.getDsr().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(pro.getVehicle() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("vehicle_id", String.valueOf(pro.getVehicle().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(pro.getDriver() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("driver_id", String.valueOf(pro.getDriver().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(pro.getHelpingHand() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("helping_hand_id", String.valueOf(pro.getHelpingHand().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
    }

    @Override
    public DeliveryUnit update(DeliveryUnitDto deliveryUnitDto) {


        DeliveryUnit pro = new DeliveryUnit(deliveryUnitDto);

        Optional<DeliveryUnit> deliveryUnit;
        try{
            int proId = deliveryUnitRepository.update(pro);
            deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(deliveryUnit.isPresent()){
            return deliveryUnit.get();
        }else{
            return null;
        }

    }

    @Override
    public List<DeliveryUnit> getAll(Pageable pageable) {
        Optional<List<DeliveryUnit>> deliveryUnits = Optional.ofNullable(deliveryUnitRepository.getAll(pageable));

        if(deliveryUnits.isPresent()){
            return deliveryUnits.get();
        }else{
            return null;
        }
    }

    @Override
    public DeliveryUnit getById(int id) {

        Optional<DeliveryUnit> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getById(id));

        if(deliveryUnit.isPresent()){
            return deliveryUnit.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return deliveryUnitRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public DeliveryUnit getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<DeliveryUnit> getAllByValue(String kye, String value) {
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
        return deliveryUnitRepository.getPageable(pageable);
    }


}
