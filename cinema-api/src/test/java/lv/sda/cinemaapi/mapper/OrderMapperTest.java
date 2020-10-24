package lv.sda.cinemaapi.mapper;


import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.entity.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrderMapperTest {

    private final OrderMapper orderMapper = new OrderMapper();

    @Test
    public void fromDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser("Andrejs Krilovs");
        orderDTO.setPersonalCode("050790-11186");
        orderDTO.setSession(1L);
        orderDTO.setPlaces("1, 2, 3");
        orderDTO.setGenerationTime("01.10.2020 14:00:00");
        orderDTO.setTotalPrice(BigDecimal.valueOf(1.13));

        Order order = orderMapper.fromDTO(orderDTO);

        assertEquals("Andrejs Krilovs", order.getUser());
        assertEquals("050790-11186", order.getPersonalCode());
        assertEquals(java.util.Optional.of(1L), Optional.ofNullable(order.getSession().getId()));
        assertEquals("1, 2, 3", order.getPlaces());
        assertEquals(BigDecimal.valueOf(1.13), order.getTotalPrice());
        assertEquals(
                LocalDateTime.parse("01.10.2020 14:00:00", DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")),
                order.getGenerationTime()
        );
    }

    @Test
    public void toDTO() {
        Order order = new Order();
        order.setUser("Andrejs Krilovs");
        order.setPersonalCode("050790-11186");
        order.setPlaces("1, 2, 3");
        order.setTotalPrice(BigDecimal.valueOf(1.13));

        Session session = new Session();
        session.setId(1L);
        order.setSession(session);

        LocalDateTime time = LocalDateTime
                .parse("01.10.2020 14:00:00", DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss"));
        order.setGenerationTime(time);

        OrderDTO orderDTO = orderMapper.toDTO(order);
        assertEquals("Andrejs Krilovs", orderDTO.getUser());
        assertEquals("050790-11186", orderDTO.getPersonalCode());
        assertEquals(Optional.of(1L), Optional.ofNullable(orderDTO.getSession()));
        assertEquals("1, 2, 3", orderDTO.getPlaces());
        assertEquals(BigDecimal.valueOf(1.13), order.getTotalPrice());
        assertEquals("01.10.2020 14:00:00", orderDTO.getGenerationTime());
    }
}