package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.entity.OrderLine;
import com.sda.onlinestore.repository.OrderLineRepository;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    public void addOrderLine(OrderLineDto orderLineDto){
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(orderLineDto.getProduct());
        orderLine.setQuantity(orderLineDto.getQuantity());
        orderLine.setPrice(orderLineDto.getPrice());
        orderLineRepository.save(orderLine);
    }

    public List<OrderLineDto> getOrderLine(){
        List<OrderLine> orderLineList = orderLineRepository.findAll();

        List<OrderLineDto> orderLineDtoList = new ArrayList<>();

        for (OrderLine orderLine: orderLineList){
            OrderLineDto orderLineDto = new OrderLineDto();
            orderLineDto.setProduct(orderLine.getProduct());
            orderLineDto.setQuantity(orderLine.getQuantity());
        }
        return orderLineDtoList;
    }

}
