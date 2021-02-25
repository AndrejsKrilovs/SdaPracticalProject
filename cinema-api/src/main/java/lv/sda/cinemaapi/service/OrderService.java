package lv.sda.cinemaapi.service;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order addOrder(Order newOrder) {
        return orderRepository.save(Objects.requireNonNull(newOrder));
    }
}
