package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Supplier;
import com.tapumandal.ims.entity.dto.SupplierDto;
import com.tapumandal.ims.service.SupplierService;
import com.tapumandal.ims.util.CommonResponseArray;
import com.tapumandal.ims.util.CommonResponseSingle;
import com.tapumandal.ims.util.ControllerHelper;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController extends ControllerHelper<Supplier> {

    @Autowired
    SupplierService supplierService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createWarehouse(@RequestBody @Valid SupplierDto supplierDto, HttpServletRequest request) {

        storeUserDetails(request);

        Supplier supplier = supplierService.create(supplierDto);

        if (supplier != null) {
            return response(true, HttpStatus.CREATED, "New supplier created successfully", supplier);
        } else if (supplier == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with data", (Supplier) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Supplier) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Supplier> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Supplier supplier = supplierService.getById(id);

        if (supplier != null) {
            return response(true, HttpStatus.FOUND, "Supplier by id: " + id, supplier);
        } else if (supplier == null) {
            return response(false, HttpStatus.NO_CONTENT, "Supplier not found or deleted", (Supplier) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Supplier) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Supplier> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Supplier> products = supplierService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, supplierService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All supplier list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No supplier found", new ArrayList<Supplier>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Supplier>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody SupplierDto supplierDto, HttpServletRequest request) {

        storeUserDetails(request);

        Supplier supplier = supplierService.update(supplierDto);

        if (supplier != null) {
            return response(true, HttpStatus.OK, "New supplier inserted successfully", supplier);
        } else if (supplier == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Supplier) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Supplier) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Supplier> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (supplierService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Supplier by id " + id + " is deleted", (Supplier) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Supplier not found or deleted", (Supplier) null);
        }
    }

}