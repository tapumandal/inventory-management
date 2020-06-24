package com.tapumandal.ims.service.implementation;

import com.tapumandal.ims.entity.Product;
import com.tapumandal.ims.entity.dto.ProductDto;
import com.tapumandal.ims.repository.ProductRepository;
import com.tapumandal.ims.service.ProductService;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Product create(ProductDto productDto) {

        Product pro = new Product(productDto);
        Optional<Product> product;

//        try{
            product = Optional.ofNullable(productRepository.create(pro));
//        }catch (Exception e){
//            return null;
//        }
//
//        if(product.isPresent()){
//            return product.get();
//        }else{
            return product.get();
//        }
    }

    @Override
    public Product update(ProductDto productDto) {


        Product pro = new Product(productDto);

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
    public List<Product> getAll(Pageable pageable) {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.getAll(pageable));

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
    public boolean deleteById(int id) {
        try {
            return productRepository.delete(id);
        }catch (Exception ex){
            return false;
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

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return productRepository.getPageable(pageable);
    }


}
