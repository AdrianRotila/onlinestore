package com.sda.onlinestore.dto;

import com.sda.onlinestore.entity.Author;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.enums.ProductType;

import javax.persistence.*;

public class ProductDto {

    private Long id;
    private String description;
    private String thumbnail;
    private CategoryDto categoryDto;
    private int price;
    private ProductTypeDto productTypeDto;
    private AuthorDto authorDto;

    public ProductDto(Long id, String description, String thumbnail, CategoryDto categoryDto, int price, ProductTypeDto productTypeDto, AuthorDto authorDto) {
        this.id = id;
        this.description = description;
        this.thumbnail = thumbnail;
        this.categoryDto = categoryDto;
        this.price = price;
        this.productTypeDto = productTypeDto;
        this.authorDto = authorDto;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductTypeDto getProductTypeDto() {
        return productTypeDto;
    }

    public void setProductTypeDto(ProductTypeDto productTypeDto) {
        this.productTypeDto = productTypeDto;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }
}