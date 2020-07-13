package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.ReceiveChallan;
import com.tapumandal.ims.entity.dto.ReceiveChallanDto;
import com.tapumandal.ims.repository.ChallanManagementRepository;
import com.tapumandal.ims.service.ChallanManagementService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallanManagementServiceImpl implements ChallanManagementService {

    @Autowired
    ChallanManagementRepository receiveChallanRepository;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    private ReceiveChallan receiveChallan;

    public ChallanManagementServiceImpl(){}

    public ChallanManagementServiceImpl(ReceiveChallan receiveChallan){
        this.receiveChallan = receiveChallan;
    }

    @Override
    public ReceiveChallan create(ReceiveChallanDto receiveChallanDto) {

        ReceiveChallan pro = new ReceiveChallan(receiveChallanDto);
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

        System.out.println(new Gson().toJson(pro));

        Optional<ReceiveChallan> receiveChallan;

//        try{
            int receiveChallanId = receiveChallanRepository.create(pro);
        System.out.println("DELIVERY UNIT ID: "+receiveChallanId);
            receiveChallan = Optional.ofNullable(receiveChallanRepository.getById(receiveChallanId));
//        }catch (Exception e){
//            return null;
//        }

        if(receiveChallan.isPresent()){
            return receiveChallan.get();
        }else{
            return null;
        }
    }

    private boolean checkVehicle(ReceiveChallan receiveChallan) {
//        if(receiveChallan.getVehicle() != null){
//            return vehicleService.isActive(receiveChallan.getVehicle().getId());
//        }
        return true;
    }

    private boolean checkUsers(ReceiveChallan receiveChallan) {

//        if(receiveChallan.getDsr() != null){
//            System.out.println("getDsr NOT NULL");
//            if(!userService.isActive(receiveChallan.getDsr().getId()))
//                return false;
//        }
//        if(receiveChallan.getDriver() != null){
//            System.out.println("getDriver NOT NULL");
//            if(!userService.isActive(receiveChallan.getDriver().getId()))
//                return false;
//        }
//        if(receiveChallan.getHelpingHand() != null){
//            System.out.println("getHelpingHand NOT NULL");
//            if(!userService.isActive(receiveChallan.getHelpingHand().getId()))
//                return false;
//        }
        return true;
    }

    @Override
    public ReceiveChallan update(ReceiveChallanDto receiveChallanDto) {


        ReceiveChallan pro = new ReceiveChallan(receiveChallanDto);

        Optional<ReceiveChallan> receiveChallan;
        try{
            int proId = receiveChallanRepository.update(pro);
            receiveChallan = Optional.ofNullable(receiveChallanRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(receiveChallan.isPresent()){
            return receiveChallan.get();
        }else{
            return null;
        }

    }

    @Override
    public List<ReceiveChallan> getAll(Pageable pageable) {
        Optional<List<ReceiveChallan>> receiveChallans = Optional.ofNullable(receiveChallanRepository.getAll(pageable));

        if(receiveChallans.isPresent()){
            return receiveChallans.get();
        }else{
            return null;
        }
    }

    @Override
    public ReceiveChallan getById(int id) {

        Optional<ReceiveChallan> receiveChallan = Optional.ofNullable(receiveChallanRepository.getById(id));

        if(receiveChallan.isPresent()){
            return receiveChallan.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return receiveChallanRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public ReceiveChallan getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<ReceiveChallan> getAllByValue(String kye, String value) {
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
        return receiveChallanRepository.getPageable(pageable);
    }


}
