package com.sda.onlinestore.entity;

import com.sda.onlinestore.enums.ProductType;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String thumbnail;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    private int price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;


    public Product(String description, String thumbnail, Category category, int price, ProductType productType, Author author) {
        this.description = description;
        this.thumbnail = thumbnail;
        this.category = category;
        this.price = price;
        this.productType = productType;
        this.author = author;
    }

    public Product() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
