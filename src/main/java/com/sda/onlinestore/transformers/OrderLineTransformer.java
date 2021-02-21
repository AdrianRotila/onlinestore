package com.sda.onlinestore.transformers;
import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.entity.OrderLine;
import com.sda.onlinestore.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderLineTransformer {

    public OrderLine transform(OrderLineDto orderLineDto) {
        OrderLine orderLine = new OrderLine();
        BeanUtils.copyProperties(orderLineDto, orderLine);
        return orderLine;
    }

    public OrderLineDto transformReversed(OrderLine orderLine) {
        OrderLineDto orderLineDto = new OrderLineDto();
        BeanUtils.copyProperties(orderLine, orderLineDto);
        return orderLineDto;
    }
}
