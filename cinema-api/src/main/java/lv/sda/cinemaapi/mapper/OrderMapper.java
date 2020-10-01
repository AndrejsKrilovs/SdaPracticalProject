package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OrderMapper {
    public Order fromDTO(OrderDTO orderDTO) {
        Order result = new Order();
        result.setUser(orderDTO.getUser());
        result.setPlaces(orderDTO.getPlaces());
        result.setTotalPrice(orderDTO.getTotalPrice());
        result.setPersonalCode(orderDTO.getPersonalCode());

        LocalDateTime generationTime = LocalDateTime
                .parse(orderDTO.getGenerationTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm"));
        result.setGenerationTime(generationTime);

        Session session = new Session();
        session.setId(orderDTO.getSession());
        result.setSession(session);
        return result;
    }

    public OrderDTO toDTO(Order order) {
        OrderDTO result = new OrderDTO();
        result.setUser(order.getUser());
        result.setPlaces(order.getPlaces());
        result.setTotalPrice(order.getTotalPrice());
        result.setPersonalCode(order.getPersonalCode());
        result.setGenerationTime(order.getGenerationTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm")));
        result.setSession(order.getSession().getId());
        return result;
    }
}
