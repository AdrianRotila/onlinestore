package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public Product transform(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public ProductDto transformReversed(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
