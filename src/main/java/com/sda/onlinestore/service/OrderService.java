package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.entity.Order;
import com.sda.onlinestore.entity.OrderLine;
import com.sda.onlinestore.entity.Product;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.repository.OrderLineRepository;
import com.sda.onlinestore.repository.OrderRepository;
import com.sda.onlinestore.repository.ProductRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setTotalCost(orderDto.getTotalCost());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setDateOfOrder(orderDto.getDateOfOrder());
        //order lines
        //user account    ??
        //order status
        orderRepository.save(order);
    }

    public void addToCart(Long userId, Long productId) {

        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(userId);
        if (userAccountOptional.isPresent()) {
            UserAccount userAccount = userAccountOptional.get();
            Order order = userAccount.getOrder();
            if (order == null) {
                order = new Order();
                userAccount.setOrder(order);
            }
            List<OrderLine> orderLineList = order.getOrderLines();
            boolean isNewItem = true;
            for (OrderLine orderLine : orderLineList) {
                if (orderLine.getProduct().getId().equals(productId)) {
                    orderLine.setQuantity(orderLine.getQuantity() + 1);
                    orderLine.setPrice(orderLine.getQuantity() * orderLine.getProduct().getPrice());
                    orderLineRepository.save(orderLine);
                    isNewItem = false;
                }
            }
            if (isNewItem){
                Optional<Product> productOptional = productRepository.findById(productId);
                if(productOptional.isPresent()){
                    OrderLine newOrderLine = new OrderLine();
                    Product product = productOptional.get();
                    newOrderLine.setProduct(product);
                    newOrderLine.setQuantity(1);
                    newOrderLine.setPrice(product.getPrice());
                    order.getOrderLines().add(newOrderLine);
                    orderRepository.save(order);
                }
            }
        }
    }

    public List<OrderDto> getOrders() {
        List<Order> orderList = orderRepository.findAll();

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setTotalCost(order.getTotalCost());
            orderDto.setDeliveryAddress(order.getDeliveryAddress());
            orderDto.setDateOfOrder(order.getDateOfOrder());
        }
        return orderDtoList;
    }

}
