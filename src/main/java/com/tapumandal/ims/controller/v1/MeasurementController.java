package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Measurement;
import com.tapumandal.ims.entity.dto.MeasurementDto;
import com.tapumandal.ims.service.MeasurementService;
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
@RequestMapping("/api/v1/measurement")
public class MeasurementController extends ControllerHelper<Measurement> {

    @Autowired
    MeasurementService measurementService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createProduct(@RequestBody @Valid MeasurementDto measurementDto, HttpServletRequest request) {

        storeUserDetails(request);

        Measurement measurement = measurementService.create(measurementDto);

        if (measurement != null) {
            return response(true, HttpStatus.CREATED, "New measurement inserted successfully", measurement);
        } else if (measurement == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with data", (Measurement) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Measurement) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Measurement> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Measurement measurement = measurementService.getById(id);

        if (measurement != null) {
            return response(true, HttpStatus.FOUND, "Measurement by id: " + id, measurement);
        } else if (measurement == null) {
            return response(false, HttpStatus.NO_CONTENT, "Measurement not found or deleted", (Measurement) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Measurement) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Measurement> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Measurement> products = measurementService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, measurementService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All measurement list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No measurement found", new ArrayList<Measurement>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Measurement>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody MeasurementDto measurementDto, HttpServletRequest request) {

        storeUserDetails(request);

        Measurement measurement = measurementService.update(measurementDto);

        if (measurement != null) {
            return response(true, HttpStatus.OK, "New measurement inserted successfully", measurement);
        } else if (measurement == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Measurement) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Measurement) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Measurement> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (measurementService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Measurement by id " + id + " is deleted", (Measurement) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Measurement not found or deleted", (Measurement) null);
        }
    }

}