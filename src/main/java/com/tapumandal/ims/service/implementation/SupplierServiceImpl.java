package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.Supplier;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.dto.SupplierDto;
import com.tapumandal.ims.repository.SupplierRepository;
import com.tapumandal.ims.service.SupplierService;
import com.tapumandal.ims.service.WarehouseService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    private Supplier supplier;

    public SupplierServiceImpl(){}

    public SupplierServiceImpl(Supplier supplier){
        this.supplier = supplier;
    }

    @Override
    public Supplier create(SupplierDto supplierDto) {

        Supplier meas = new Supplier(supplierDto);

        Optional<Supplier> supplier;

//        try{
            int supplierId = supplierRepository.create(meas);
            supplier = Optional.ofNullable(supplierRepository.getById(supplierId));
//        }catch (Exception e){
//            return null;
//        }

        if(supplier.isPresent()){
            return supplier.get();
        }else{
            return null;
        }
    }

    @Override
    public Supplier update(SupplierDto supplierDto) {

        Supplier meas = new Supplier(supplierDto);

        Optional<Supplier> supplier;
//        try{
            int supplierId = supplierRepository.update(meas);
            supplier = Optional.ofNullable(supplierRepository.getById(supplierId));
//        }catch (Exception e){
//            return null;
//        }

        if(supplier.isPresent()){
            return supplier.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Supplier> getAll(Pageable pageable) {
        Optional<List<Supplier>> products = Optional.ofNullable(supplierRepository.getAll(pageable));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public Supplier getById(int id) {

        Optional<Supplier> supplier = Optional.ofNullable(supplierRepository.getById(id));

        if(supplier.isPresent()){
            return supplier.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return supplierRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Supplier getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Supplier> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Supplier> supplier = Optional.ofNullable(supplierRepository.getById(id));
        if(supplier.isPresent()){
            if(supplier.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return supplier.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return supplierRepository.getPageable(pageable);
    }


}
