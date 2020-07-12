package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.Vehicle;
import com.tapumandal.ims.entity.dto.VehicleDto;
import com.tapumandal.ims.repository.VehicleRepository;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.service.VehicleService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    private Vehicle vehicle;

    public VehicleServiceImpl(){}

    public VehicleServiceImpl(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public Vehicle create(VehicleDto vehicleDto) {

        Vehicle pro = new Vehicle(vehicleDto);
        Optional<Vehicle> vehicle;

//        try{
            int vehicleId = vehicleRepository.create(pro);
            vehicle = Optional.ofNullable(vehicleRepository.getById(vehicleId));
//        }catch (Exception e){
//            return null;
//        }

        if(vehicle.isPresent()){
            return vehicle.get();
        }else{
            return null;
        }
    }

    @Override
    public Vehicle update(VehicleDto vehicleDto) {


        Vehicle pro = new Vehicle(vehicleDto);

        Optional<Vehicle> vehicle;
        try{
            int proId = vehicleRepository.update(pro);
            vehicle = Optional.ofNullable(vehicleRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(vehicle.isPresent()){
            return vehicle.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Vehicle> getAll(Pageable pageable) {
        Optional<List<Vehicle>> vehicles = Optional.ofNullable(vehicleRepository.getAll(pageable));

        if(vehicles.isPresent()){
            return vehicles.get();
        }else{
            return null;
        }
    }

    @Override
    public Vehicle getById(int id) {

        Optional<Vehicle> vehicle = Optional.ofNullable(vehicleRepository.getById(id));

        if(vehicle.isPresent()){
            return vehicle.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return vehicleRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Vehicle getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Vehicle> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Vehicle> vehicle = Optional.ofNullable(vehicleRepository.getById(id));
        if(vehicle.isPresent()){
            if(vehicle.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return vehicle.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return vehicleRepository.getPageable(pageable);
    }


}
