package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.mapper.OrderMapper;
import lv.sda.cinemaapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order.svc")
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/Order")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO order) {
        Order newOrder = orderService.addOrder(orderMapper.fromDTO(order));
        return new ResponseEntity<>(orderMapper.toDTO(newOrder), HttpStatus.CREATED);
    }
}
