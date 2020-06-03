package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.repository.ProductRepository;
import com.tapumandal.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private Product product;

    public ProductServiceImpl(){}

    public ProductServiceImpl(Product product){
        this.product = product;
    }

    @Override
    public List<Product> create(Product pro) {
        Optional<Product> product;

        try{
            product = Optional.ofNullable(productRepository.create(pro));
        }catch (Exception e){
            return null;
        }

        if(product.isPresent()){
            return productRepository.getAll();
        }else{
            return null;
        }
    }

    @Override
    public Product update(Product pro) {

        Optional<Product> product;
        try{
            product = Optional.ofNullable(productRepository.update(pro));
        }catch (Exception e){
            return null;
        }

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Product> getAll() {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.getAll());

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }

    @Override
    public Product getById(int id) {

        Optional<Product> product = Optional.ofNullable(productRepository.getById(id));

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public Product getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Product> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return product.isActive();
    }

    @Override
    public boolean isDeleted(int id) {
        return product.isDeleted();
    }


}
