package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    public Order fromDTO(OrderDTO orderDTO) {
        Order result = new Order();
        result.setUser(orderDTO.getUser());
        result.setPlaces(orderDTO.getPlaces());
        result.setTotalPrice(orderDTO.getTotalPrice());
        result.setPersonalCode(orderDTO.getPersonalCode());

        LocalDateTime generationTime = orderDTO.getGenerationTime();
        result.setGenerationTime(generationTime);

        Session session = new Session();
        session.setId(orderDTO.getSession());
        result.setSession(session);
        return result;
    }

    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .generationTime(order.getGenerationTime())
                .personalCode(order.getPersonalCode())
                .totalPrice(order.getTotalPrice())
                .places(order.getPlaces())
                .user(order.getUser())
                .build();
    }
}
