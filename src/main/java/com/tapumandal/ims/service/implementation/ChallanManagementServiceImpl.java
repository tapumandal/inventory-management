package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.Challan;
import com.tapumandal.ims.entity.dto.ChallanDto;
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

    private Challan challan;

    public ChallanManagementServiceImpl(){}

    public ChallanManagementServiceImpl(Challan challan){
        this.challan = challan;
    }

    @Override
    public Challan create(ChallanDto challanDto) {
        System.out.println("DTO:");
        System.out.println(new Gson().toJson(challanDto));
        Challan pro = new Challan(challanDto);
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


        Optional<Challan> challan;

//        try{
            int receiveChallanId = receiveChallanRepository.create(pro);
        System.out.println("DELIVERY UNIT ID: "+receiveChallanId);
            challan = Optional.ofNullable(receiveChallanRepository.getById(receiveChallanId));
//        }catch (Exception e){
//            return null;
//        }

        if(challan.isPresent()){
            return challan.get();
        }else{
            return null;
        }
    }

    private boolean checkVehicle(Challan challan) {
//        if(challan.getVehicle() != null){
//            return vehicleService.isActive(challan.getVehicle().getId());
//        }
        return true;
    }

    private boolean checkUsers(Challan challan) {

//        if(challan.getDsr() != null){
//            System.out.println("getDsr NOT NULL");
//            if(!userService.isActive(challan.getDsr().getId()))
//                return false;
//        }
//        if(challan.getDriver() != null){
//            System.out.println("getDriver NOT NULL");
//            if(!userService.isActive(challan.getDriver().getId()))
//                return false;
//        }
//        if(challan.getHelpingHand() != null){
//            System.out.println("getHelpingHand NOT NULL");
//            if(!userService.isActive(challan.getHelpingHand().getId()))
//                return false;
//        }
        return true;
    }

    @Override
    public Challan update(ChallanDto challanDto) {


        Challan pro = new Challan(challanDto);

        Optional<Challan> challan;
//        try{
            int proId = receiveChallanRepository.update(pro);
            challan = Optional.ofNullable(receiveChallanRepository.getById(proId));
//        }catch (Exception e){
//            return null;
//        }

        if(challan.isPresent()){
            return challan.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Challan> getAll(Pageable pageable) {
        Optional<List<Challan>> receiveChallans = Optional.ofNullable(receiveChallanRepository.getAll(pageable));

        if(receiveChallans.isPresent()){
            return receiveChallans.get();
        }else{
            return null;
        }
    }

    @Override
    public Challan getById(int id) {

        Optional<Challan> challan = Optional.ofNullable(receiveChallanRepository.getById(id));

        if(challan.isPresent()){
            return challan.get();
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
    public Challan getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Challan> getAllByValue(String kye, String value) {
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
