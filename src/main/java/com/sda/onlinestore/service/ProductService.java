package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.entity.Product;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.ProductRepository;
import com.sda.onlinestore.transformers.ProductTransformer;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTransformer productTransformer;

    public void addProduct(ProductDto productDto){
        Product product = productTransformer.transform(productDto);
        productRepository.save(product);
    }

    public List<ProductDto> getProducts(){

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product:products){
            ProductDto productDto = productTransformer.transformReversed(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            System.out.println(product.toString());
            return product;
        } else {
            System.out.println("Product with ID " + id + " does not exist");
            throw new NotFoundException("Product with ID " + id + " does not exist");
        }
    }

    public List<Product> findProductByProductType(String productType) {
        return this.productRepository.findProductByProductType(productType);
    }

    public void deleteProductById(Long id) {
        this.findProductById(id);
        productRepository.deleteById(id);
    }
}
