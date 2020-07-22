package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.DeliveryUnit;
import com.tapumandal.ims.entity.dto.DeliveryUnitDto;
import com.tapumandal.ims.service.DeliveryUnitService;
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
@RequestMapping("/api/v1/deliveryunit")
public class DeliveryUnitController extends ControllerHelper<DeliveryUnit> {

    @Autowired
    DeliveryUnitService deliveryUnitService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createDeliveryUnit(@RequestBody @Valid DeliveryUnitDto deliveryUnitDto, HttpServletRequest request) {

        storeUserDetails(request);

        DeliveryUnit deliveryUnit = deliveryUnitService.create(deliveryUnitDto);

        if (deliveryUnit != null) {
            return response(true, HttpStatus.CREATED, "New deliveryUnit inserted successfully", deliveryUnit);
        } else if (deliveryUnit == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (DeliveryUnit) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (DeliveryUnit) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<DeliveryUnit> getDeliveryUnit(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        DeliveryUnit deliveryUnit = deliveryUnitService.getById(id);

        if (deliveryUnit != null) {
            return response(true, HttpStatus.FOUND, "DeliveryUnit by id: " + id, deliveryUnit);
        } else if (deliveryUnit == null) {
            return response(false, HttpStatus.NO_CONTENT, "DeliveryUnit not found or deleted", (DeliveryUnit) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (DeliveryUnit) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<DeliveryUnit> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<DeliveryUnit> deliveryUnits = deliveryUnitService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, deliveryUnitService.getPageable(pageable), pageable);

        if (!deliveryUnits.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All deliveryUnit list", deliveryUnits, myPagenation);
        } else if (deliveryUnits.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No deliveryUnit found", new ArrayList<DeliveryUnit>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<DeliveryUnit>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateDeliveryUnit(@RequestBody DeliveryUnitDto deliveryUnitDto, HttpServletRequest request) {

        storeUserDetails(request);

        DeliveryUnit deliveryUnit = deliveryUnitService.update(deliveryUnitDto);

        if (deliveryUnit != null) {
            return response(true, HttpStatus.OK, "New deliveryUnit inserted successfully", deliveryUnit);
        } else if (deliveryUnit == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (DeliveryUnit) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (DeliveryUnit) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<DeliveryUnit> deleteDeliveryUnit(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (deliveryUnitService.deleteById(id)) {
            return response(true, HttpStatus.OK, "DeliveryUnit by id " + id + " is deleted", (DeliveryUnit) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "DeliveryUnit not found or deleted", (DeliveryUnit) null);
        }
    }

}