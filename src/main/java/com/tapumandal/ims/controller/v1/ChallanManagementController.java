package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Challan;
import com.tapumandal.ims.entity.dto.ChallanDto;
import com.tapumandal.ims.service.ChallanManagementService;
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
@RequestMapping("/api/v1/challan")
public class ChallanManagementController extends ControllerHelper<Challan> {

    @Autowired
    ChallanManagementService challanManagementService;

    @PostMapping(path = "/create")
    public CommonResponseSingle<Challan> createProduct(@RequestBody @Valid ChallanDto challanDto, HttpServletRequest request) {

//        Challan challan = new Challan(receiveChallanDto);
        storeUserDetails(request);

        Challan challan = challanManagementService.create(challanDto);

        if (challan != null) {
            return response(true, HttpStatus.CREATED, "New challan inserted successfully", challan);
        } else if (challan == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Challan) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Challan) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Challan> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Challan challan = challanManagementService.getById(id);

        if (challan != null) {
            return response(true, HttpStatus.FOUND, "Challan by id: " + id, challan);
        } else if (challan == null) {
            return response(false, HttpStatus.NO_CONTENT, "Challan not found or deleted", (Challan) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Challan) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Challan> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Challan> products = challanManagementService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, challanManagementService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All challan list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No challan found", new ArrayList<Challan>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Challan>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody ChallanDto challanDto, HttpServletRequest request) {

        storeUserDetails(request);

        Challan challan = challanManagementService.update(challanDto);

        if (challan != null) {
            return response(true, HttpStatus.OK, "New Challan inserted successfully", challan);
        } else if (challan == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Challan) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Challan) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Challan> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (challanManagementService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Challan by id " + id + " is deleted", (Challan) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Challan not found or deleted", (Challan) null);
        }
    }

}