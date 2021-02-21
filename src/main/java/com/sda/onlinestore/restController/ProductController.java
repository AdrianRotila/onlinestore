package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.entity.Product;
import com.sda.onlinestore.service.CategoryService;
import com.sda.onlinestore.service.ProductService;
import com.sda.onlinestore.transformers.CategoryTransformer;
import com.sda.onlinestore.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    private final ProductTransformer productTransformer;

    @Autowired
    public ProductController(ProductService productService, ProductTransformer productTransformer) {
        this.productService = productService;
        this.productTransformer = productTransformer;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productTransformer.transform(productDto);
        Product savedProduct = productService.saveProduct(product);
        ProductDto savedProductDto = productTransformer.transformReversed(savedProduct);
        return ResponseEntity.ok(savedProductDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtoList = productService.getProducts();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        ProductDto productDto = productTransformer.transformReversed(product);
        return ResponseEntity.ok(productDto);
    }

//    @GetMapping
////    public ResponseEntity<List<ProductDto>> getProductByProductType(@RequestParam(value = "productType") String productType) {
////        List<Product> products = productService.findProductByProductType(productType);
////        List<ProductDto> productDtos = products.stream().map(productTransformer::transformReversed).collect(Collectors.toList());
////        return ResponseEntity.ok(productDtos);
////    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product product = productTransformer.transform(productDto);
        Product savedProduct = productService.saveProduct(product);
        ProductDto savedProductDto = productTransformer.transformReversed(savedProduct);
        return ResponseEntity.ok(savedProductDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}