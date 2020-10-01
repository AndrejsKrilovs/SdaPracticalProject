package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }
}
