package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.entity.dto.ChallanDto;
import com.tapumandal.ims.repository.ChallanManagementRepository;
import com.tapumandal.ims.service.*;
import com.tapumandal.ims.util.MyPagenation;
import com.tapumandal.ims.util.ResourceVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallanManagementServiceImpl implements ChallanManagementService {

    @Autowired
    ChallanManagementRepository receiveChallanRepository;

    @Autowired
    ResourceVerifier resourceVerifier;

    private Challan challan;

    public ChallanManagementServiceImpl(){}

    public ChallanManagementServiceImpl(Challan challan){
        this.challan = challan;
    }

    @Override
    public Challan create(ChallanDto challanDto) {

        Challan cha = new Challan(challanDto);

        System.out.println(new Gson().toJson(cha));

        if(!resourceVerifier.checkSupplier(cha.getSupplier().getId())){
            System.out.println("CHECK Suppliar Id");
            return null;
        }
        if(!resourceVerifier.checkChallanProduct(cha.getChallanProducts())){
            System.out.println("CHECK getChallanProducts");
            return null;
        }


        Optional<Challan> challan;

//        try{
            int receiveChallanId = receiveChallanRepository.create(cha);
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


    @Override
    public Challan update(ChallanDto challanDto) {


        Challan cha = new Challan(challanDto);

        Optional<Challan> challan;
//        try{
            int proId = receiveChallanRepository.update(cha);
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
        Optional<Challan> challan = Optional.ofNullable(receiveChallanRepository.getById(id));
        if(challan.isPresent()){
            if(challan.get().isActive()){
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
        return receiveChallanRepository.getPageable(pageable);
    }


}
