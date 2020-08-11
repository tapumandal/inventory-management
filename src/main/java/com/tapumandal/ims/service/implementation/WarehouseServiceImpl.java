package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.Warehouse;
import com.tapumandal.ims.entity.dto.WarehouseDto;
import com.tapumandal.ims.repository.WarehouseRepository;
import com.tapumandal.ims.service.MeasurementService;
import com.tapumandal.ims.service.WarehouseService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    private Warehouse warehouse;

    public WarehouseServiceImpl(){}

    public WarehouseServiceImpl(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    @Override
    public Warehouse create(WarehouseDto warehouseDto) {

        Warehouse meas = new Warehouse(warehouseDto);

        Optional<Warehouse> warehouse;

//        try{
            int measurementId = warehouseRepository.create(meas);
            warehouse = Optional.ofNullable(warehouseRepository.getById(measurementId));
//        }catch (Exception e){
//            return null;
//        }

        if(warehouse.isPresent()){
            return warehouse.get();
        }else{
            return null;
        }
    }

    @Override
    public Warehouse update(WarehouseDto warehouseDto) {

        Warehouse meas = new Warehouse(warehouseDto);

        Optional<Warehouse> warehouse;
//        try{
            int measurementId = warehouseRepository.update(meas);
            warehouse = Optional.ofNullable(warehouseRepository.getById(measurementId));
//        }catch (Exception e){
//            return null;
//        }

        if(warehouse.isPresent()){
            return warehouse.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Warehouse> getAll(Pageable pageable) {
        Optional<List<Warehouse>> products = Optional.ofNullable(warehouseRepository.getAll(pageable));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public Warehouse getById(int id) {

        Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.getById(id));

        if(warehouse.isPresent()){
            return warehouse.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return warehouseRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Warehouse getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Warehouse> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Warehouse> warehouse = Optional.ofNullable(warehouseRepository.getById(id));
        if(warehouse.isPresent()){
            if(warehouse.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return warehouse.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return warehouseRepository.getPageable(pageable);
    }


}
