package com.sda.onlinestore.dto;

import java.util.List;

public class CategoryDto {

    private Long id;
    private List<ProductDto> productDtoList;
    private String name;

    public CategoryDto(Long id, String name, List<ProductDto> productDtoList) {
        this.id = id;
        this.name = name;
        this.productDtoList = productDtoList;
    }

    public CategoryDto() {
    }


    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
