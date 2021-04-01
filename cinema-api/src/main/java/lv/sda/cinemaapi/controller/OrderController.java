package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.OrderDTO;
import lv.sda.cinemaapi.dto.PreOrderDTO;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.service.FilmService;
import lv.sda.cinemaapi.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/order.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final FilmService filmService;
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;

    @PostMapping(path = "/Calculation")
    public OrderDTO generateOrderData(@RequestBody PreOrderDTO preOrderDTO) {
        SessionDTO sessionDTO = sessionMapper.generateSession(sessionService
                .getById(preOrderDTO.getSessionNumber()));

        return OrderDTO.builder()
                .places(preOrderDTO.getPlaces().stream().map(String::valueOf).collect(Collectors.joining(",")))
                .totalPrice(sessionDTO.getPrice().multiply(BigDecimal.valueOf(preOrderDTO.getPlaces().size())))
                .filmName(filmService.getFilmById(preOrderDTO.getFilmNumber()).getTitle())
                .sessionDate(sessionDTO.getDateTime())
                .currency(sessionDTO.getCurrency())
                .roomNumber(sessionDTO.getRoom())
                .build();
    }
}
