package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Delivery;
import com.tapumandal.ims.entity.dto.DeliveryDto;
import com.tapumandal.ims.service.DeliveryService;
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
@RequestMapping("/api/v1/delivery")
public class DeliveryController extends ControllerHelper<Delivery> {

    @Autowired
    DeliveryService deliverySerice;

    @PostMapping(path = "/create")
    public CommonResponseSingle<Delivery> createProduct(@RequestBody @Valid DeliveryDto deliveryDto, HttpServletRequest request) {

//        Delivery delivery = new Delivery(receiveChallanDto);
        storeUserDetails(request);

        Delivery delivery = deliverySerice.create(deliveryDto);

        if (delivery != null) {
            return response(true, HttpStatus.CREATED, "New delivery inserted successfully", delivery);
        } else if (delivery == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Delivery) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Delivery) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Delivery> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Delivery delivery = deliverySerice.getById(id);

        if (delivery != null) {
            return response(true, HttpStatus.FOUND, "Delivery by id: " + id, delivery);
        } else if (delivery == null) {
            return response(false, HttpStatus.NO_CONTENT, "Delivery not found or deleted", (Delivery) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Delivery) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Delivery> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Delivery> products = deliverySerice.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, deliverySerice.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All delivery list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No delivery found", new ArrayList<Delivery>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Delivery>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody DeliveryDto deliveryDto, HttpServletRequest request) {

        storeUserDetails(request);

        Delivery delivery = deliverySerice.update(deliveryDto);

        if (delivery != null) {
            return response(true, HttpStatus.OK, "New Delivery inserted successfully", delivery);
        } else if (delivery == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Delivery) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Delivery) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Delivery> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (deliverySerice.deleteById(id)) {
            return response(true, HttpStatus.OK, "Delivery by id " + id + " is deleted", (Delivery) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Delivery not found or deleted", (Delivery) null);
        }
    }

}