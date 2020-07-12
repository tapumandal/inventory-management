package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Vehicle;
import com.tapumandal.ims.entity.dto.VehicleDto;
import com.tapumandal.ims.service.VehicleService;
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
@RequestMapping("/api/v1/vehicle")
public class VehicleController extends ControllerHelper<Vehicle> {

    @Autowired
    VehicleService vehicleService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createVehicle(@RequestBody @Valid VehicleDto vehicleDto, HttpServletRequest request) {

        storeUserDetails(request);

        Vehicle vehicle = vehicleService.create(vehicleDto);

        if (vehicle != null) {
            return response(true, HttpStatus.CREATED, "New vehicle inserted successfully", vehicle);
        } else if (vehicle == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Vehicle) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Vehicle) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Vehicle> getVehicle(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Vehicle vehicle = vehicleService.getById(id);

        if (vehicle != null) {
            return response(true, HttpStatus.FOUND, "Vehicle by id: " + id, vehicle);
        } else if (vehicle == null) {
            return response(false, HttpStatus.NO_CONTENT, "Vehicle not found or deleted", (Vehicle) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Vehicle) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Vehicle> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Vehicle> vehicles = vehicleService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, vehicleService.getPageable(pageable), pageable);

        if (!vehicles.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All vehicle list", vehicles, myPagenation);
        } else if (vehicles.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No vehicle found", new ArrayList<Vehicle>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Vehicle>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateVehicle(@RequestBody VehicleDto vehicleDto, HttpServletRequest request) {

        storeUserDetails(request);

        Vehicle vehicle = vehicleService.update(vehicleDto);

        if (vehicle != null) {
            return response(true, HttpStatus.OK, "New vehicle inserted successfully", vehicle);
        } else if (vehicle == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Vehicle) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Vehicle) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Vehicle> deleteVehicle(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (vehicleService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Vehicle by id " + id + " is deleted", (Vehicle) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Vehicle not found or deleted", (Vehicle) null);
        }
    }

}