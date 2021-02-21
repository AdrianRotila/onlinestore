package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.entity.Author;
import com.sda.onlinestore.entity.Order;
import com.sda.onlinestore.entity.OrderLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderTransformer {

    public Order transform(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        return order;
    }

    public OrderDto transformReversed(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }
}
