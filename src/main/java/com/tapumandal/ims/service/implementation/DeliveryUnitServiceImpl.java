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
import com.tapumandal.ims.util.ResourceVerifier;
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

    @Autowired
    ResourceVerifier resourceVerifier;

    private DeliveryUnit deliveryUnit;

    public DeliveryUnitServiceImpl(){}

    public DeliveryUnitServiceImpl(DeliveryUnit deliveryUnit){
        this.deliveryUnit = deliveryUnit;
    }

    @Override
    public DeliveryUnit create(DeliveryUnitDto deliveryUnitDto) {


        System.out.println(new Gson().toJson(deliveryUnitDto));
        DeliveryUnit dUnit = new DeliveryUnit(deliveryUnitDto);
        System.out.println(new Gson().toJson(dUnit));

        if(!resourceVerifier.checkUser(dUnit.getDsr().getId()) ){
            System.out.println("CHECK DSR");
            return null;
        }

        if(dUnit.getDriver() != null){
            if(!resourceVerifier.checkUser(dUnit.getDriver().getId())){
                System.out.println("CHECK Driver");
                return null;
            }
        }

        if(dUnit.getHelpingHand() != null){
            if(!resourceVerifier.checkUser(dUnit.getHelpingHand().getId())){
                System.out.println("CHECK Helping Hand");
                return null;
            }
        }

        if(dUnit.getVehicle() != null){
            if(!resourceVerifier.checkVehicle(dUnit.getVehicle().getId())){
                System.out.println("CHECK VEHICLE");
                return null;
            }
        }

        System.out.println(new Gson().toJson(dUnit));

        removeUserFromAnotherDeliveryUnit(dUnit);

        System.out.println("AFTER REMOVE DELIVERY UNIT");

        Optional<DeliveryUnit> deliveryUnit;

//        try{
            int deliveryUnitId = deliveryUnitRepository.create(dUnit);
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

    private void removeUserFromAnotherDeliveryUnit(DeliveryUnit dUnit) {

        if(dUnit.getDsr() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("dsr_id", String.valueOf(dUnit.getDsr().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(dUnit.getVehicle() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("vehicle_id", String.valueOf(dUnit.getVehicle().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(dUnit.getDriver() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("driver_id", String.valueOf(dUnit.getDriver().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
        if(dUnit.getHelpingHand() != null){
            Optional<List<DeliveryUnit>> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getByKeyAndValue("helping_hand_id", String.valueOf(dUnit.getHelpingHand().getId())));
            if(deliveryUnit.isPresent() && !deliveryUnit.get().isEmpty()){
                deliveryUnitRepository.delete(deliveryUnit.get().get(0).getId());
            }
        }
    }

    @Override
    public DeliveryUnit update(DeliveryUnitDto deliveryUnitDto) {


        DeliveryUnit dUnit = new DeliveryUnit(deliveryUnitDto);

        Optional<DeliveryUnit> deliveryUnit;
        try{
            int dUnitId = deliveryUnitRepository.update(dUnit);
            deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getById(dUnitId));
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
        Optional<DeliveryUnit> deliveryUnit = Optional.ofNullable(deliveryUnitRepository.getById(id));
        if(deliveryUnit.isPresent()){
            if(deliveryUnit.get() != null){
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
        return deliveryUnitRepository.getPageable(pageable);
    }


}
