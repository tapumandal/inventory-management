package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.entity.dto.*;
import com.tapumandal.ims.service.ProductService;
import com.tapumandal.ims.util.ControllerHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends ControllerHelper<Product> {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/create")
    public CommonResponseArray createProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {

        Product product = new Product(productDto);

        List<Product> products = productService.create(product);

        if (products != null) {
            return response(true, HttpStatus.CREATED, "New product inserted successfully", products);
        } else if (products == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with database", (List<Product>) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (List<Product>) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Product> getProduct(@PathVariable("id") int id, HttpServletRequest request) {

        Product product = productService.getById(id);

        if (product != null) {
            return response(true, HttpStatus.FOUND, "Product by id: " + id, product);
        } else if (product == null) {
            return response(false, HttpStatus.ACCEPTED, "Product not found or deleted", (Product) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Product) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Product> getAll(HttpServletRequest request) {

        List<Product> products = productService.getAll();

        if (!products.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All product list", products);
        } else if (products.isEmpty()) {
            return response(false, HttpStatus.ACCEPTED, "No product added yet", new ArrayList<Product>());
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Product>());
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {

        Product pro = new Product(productDto);

        Product product = productService.update(pro);

        if (product != null) {
            return response(true, HttpStatus.CREATED, "New product inserted successfully", product);
        } else if (product == null) {
            return response(false, HttpStatus.NOT_ACCEPTABLE, "Something is wrong with database", (Product) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Product) null);
    }

}