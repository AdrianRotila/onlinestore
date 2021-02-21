package com.sda.onlinestore.restController;
import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @PostMapping("/api/addOrderLine")
    public ResponseEntity addOrderLine(@RequestBody OrderLineDto orderLineDto){
        orderLineService.addOrderLine(orderLineDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/getAllOrderLines")
    public ResponseEntity getAllOrderLines(){
        List<OrderLineDto> orderLineDtoList = orderLineService.getOrderLine();
        return ResponseEntity.ok(orderLineDtoList);
    }
}
