package com.sda.onlinestore.restController;
import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/addOrder")
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/getAllOrders")
    public ResponseEntity getAllOrders(){
        List<OrderDto> orderDtoList = orderService.getOrders();
        return ResponseEntity.ok(orderDtoList);
    }
}
