package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Warehouse;
import com.tapumandal.ims.entity.Warehouse;
import com.tapumandal.ims.entity.dto.WarehouseDto;
import com.tapumandal.ims.service.WarehouseService;
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
@RequestMapping("/api/v1/warehouse")
public class WarehouseController extends ControllerHelper<Warehouse> {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createWarehouse(@RequestBody @Valid WarehouseDto warehouseDto, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.create(warehouseDto);

        if (warehouse != null) {
            return response(true, HttpStatus.CREATED, "New warehouse created successfully", warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with data", (Warehouse) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Warehouse) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Warehouse> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.getById(id);

        if (warehouse != null) {
            return response(true, HttpStatus.FOUND, "Warehouse by id: " + id, warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.NO_CONTENT, "Warehouse not found or deleted", (Warehouse) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Warehouse) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Warehouse> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Warehouse> products = warehouseService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, warehouseService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All warehouse list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No warehouse found", new ArrayList<Warehouse>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Warehouse>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody WarehouseDto warehouseDto, HttpServletRequest request) {

        storeUserDetails(request);

        Warehouse warehouse = warehouseService.update(warehouseDto);

        if (warehouse != null) {
            return response(true, HttpStatus.OK, "New warehouse inserted successfully", warehouse);
        } else if (warehouse == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Warehouse) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Warehouse) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Warehouse> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (warehouseService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Warehouse by id " + id + " is deleted", (Warehouse) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Warehouse not found or deleted", (Warehouse) null);
        }
    }

}