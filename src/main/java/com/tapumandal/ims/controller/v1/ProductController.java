package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.entity.dto.ProductDto;
import com.tapumandal.ims.service.ProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController extends ControllerHelper<Product> {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createProduct(@RequestBody @Valid ProductDto productDto, HttpServletRequest request) {

        storeUserDetails(request);

        Product product = productService.create(productDto);

        if (product != null) {
            return response(true, HttpStatus.CREATED, "New product inserted successfully", product);
        } else if (product == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Product) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Product) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Product> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Product product = productService.getById(id);

        if (product != null) {
            return response(true, HttpStatus.FOUND, "Product by id: " + id, product);
        } else if (product == null) {
            return response(false, HttpStatus.NO_CONTENT, "Product not found or deleted", (Product) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Product) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Product> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Product> products = productService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, productService.getPageable(pageable), pageable);

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All product list", products, myPagenation);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No product found", new ArrayList<Product>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Product>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {

        storeUserDetails(request);

        Product product = productService.update(productDto);

        if (product != null) {
            return response(true, HttpStatus.OK, "New product inserted successfully", product);
        } else if (product == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Product) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Product) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Product> deleteProduct(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (productService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Product by id " + id + " is deleted", (Product) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Product not found or deleted", (Product) null);
        }
    }

}