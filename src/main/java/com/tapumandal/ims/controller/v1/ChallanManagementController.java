package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.ReceiveChallan;
import com.tapumandal.ims.entity.ReceiveChallan;
import com.tapumandal.ims.entity.dto.ReceiveChallanDto;
import com.tapumandal.ims.entity.dto.ReceiveChallanDto;
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
public class ChallanManagementController extends ControllerHelper<ReceiveChallan> {

    @Autowired
    ChallanManagementService challanManagementService;

    @PostMapping(path = "/create")
    public CommonResponseSingle<ReceiveChallan> createProduct(@RequestBody @Valid ReceiveChallanDto receiveChallanDto, HttpServletRequest request) {

//        ReceiveChallan receiveChallan = new ReceiveChallan(receiveChallanDto);
        storeUserDetails(request);

        ReceiveChallan receiveChallan = challanManagementService.create(receiveChallanDto);

        if (receiveChallan != null) {
            return response(true, HttpStatus.CREATED, "New receiveChallan inserted successfully", receiveChallan);
        } else if (receiveChallan == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (ReceiveChallan) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (ReceiveChallan) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<ReceiveChallan> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        ReceiveChallan receiveChallan = challanManagementService.getById(id);

        if (receiveChallan != null) {
            return response(true, HttpStatus.FOUND, "ReceiveChallan by id: " + id, receiveChallan);
        } else if (receiveChallan == null) {
            return response(false, HttpStatus.NO_CONTENT, "ReceiveChallan not found or deleted", (ReceiveChallan) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (ReceiveChallan) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<ReceiveChallan> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<ReceiveChallan> products = challanManagementService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, challanManagementService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All receiveChallan list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No receiveChallan found", new ArrayList<ReceiveChallan>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<ReceiveChallan>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody ReceiveChallanDto receiveChallanDto, HttpServletRequest request) {

        storeUserDetails(request);

        ReceiveChallan receiveChallan = challanManagementService.update(receiveChallanDto);

        if (receiveChallan != null) {
            return response(true, HttpStatus.OK, "New Challan inserted successfully", receiveChallan);
        } else if (receiveChallan == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (ReceiveChallan) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (ReceiveChallan) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<ReceiveChallan> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (challanManagementService.deleteById(id)) {
            return response(true, HttpStatus.OK, "ReceiveChallan by id " + id + " is deleted", (ReceiveChallan) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "ReceiveChallan not found or deleted", (ReceiveChallan) null);
        }
    }

}