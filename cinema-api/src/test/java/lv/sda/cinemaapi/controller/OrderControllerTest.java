package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.OrderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    private final static String URL_TO_TEST = "http://localhost:%d/api/order.svc%s";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addOrderTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser("Andrejs Krilovs");
        orderDTO.setPersonalCode("050790-11186");
        orderDTO.setSession(1L);
        orderDTO.setPlaces("1, 2, 3");
        orderDTO.setGenerationTime("01.10.2020 14:00");
        orderDTO.setTotalPrice(BigDecimal.valueOf(1.13));

        String url = String.format(URL_TO_TEST, port, "/Order");
        ResponseEntity<OrderDTO> entity = restTemplate.postForEntity(url, orderDTO, OrderDTO.class);

        Assertions.assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }
}