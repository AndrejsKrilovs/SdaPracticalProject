package lv.sda.cinemaapi.mapper;

import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.entity.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        Assertions.assertEquals("Andrejs Krilovs", order.getUser());
        Assertions.assertEquals("050790-11186", order.getPersonalCode());
        Assertions.assertEquals(1L, order.getSession().getId());
        Assertions.assertEquals("1, 2, 3", order.getPlaces());
        Assertions.assertEquals(BigDecimal.valueOf(1.13), order.getTotalPrice());
        Assertions.assertEquals(
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
        Assertions.assertEquals("Andrejs Krilovs", orderDTO.getUser());
        Assertions.assertEquals("050790-11186", orderDTO.getPersonalCode());
        Assertions.assertEquals(1L, orderDTO.getSession());
        Assertions.assertEquals("1, 2, 3", orderDTO.getPlaces());
        Assertions.assertEquals(BigDecimal.valueOf(1.13), order.getTotalPrice());
        Assertions.assertEquals("01.10.2020 14:00:00", orderDTO.getGenerationTime());
    }
}