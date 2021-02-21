package com.sda.onlinestore.entity;


import com.sda.onlinestore.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalCost;

    @ManyToOne
    private Address deliveryAddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfOrder;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToOne
    private UserAccount userAccount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(double totalCost, Address deliveryAddress, Date dateOfOrder, List<OrderLine> orderLines, UserAccount userAccount, OrderStatus orderStatus) {
        this.totalCost = totalCost;
        this.deliveryAddress = deliveryAddress;
        this.dateOfOrder = dateOfOrder;
        this.orderLines = orderLines;
        this.userAccount = userAccount;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}