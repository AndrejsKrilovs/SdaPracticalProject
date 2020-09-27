package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.service.PlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceControllerIntegrationTest {

    private final static String URL_TO_TEST = "http://localhost:%d/api/place.svc%s";
    private final static Integer TOTAL_PLACE_NUMBER = 40;
    private final static Integer CURRENT_PLACE_NUMBER = 1;
    private final static Integer CURRENT_ROOM_NUMBER = 1;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PlaceService placeService;

    @Test
    public void getPlacesByRoomNumberTest() {
        String url = String.format(URL_TO_TEST, port, "/Places(4)");
        ResponseEntity<PlaceDTO[]> entity = restTemplate.getForEntity(url, PlaceDTO[].class);

        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(TOTAL_PLACE_NUMBER, Objects.requireNonNull(entity.getBody()).length);
    }

    @Test
    public void updatePlaceTest() {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setAvailable(Boolean.FALSE);
        placeDTO.setRoomNumber(CURRENT_ROOM_NUMBER);
        placeDTO.setPlaceNumber(CURRENT_PLACE_NUMBER);

        String url = String.format(URL_TO_TEST, port, "/Place");
        ResponseEntity<PlaceDTO> updatedEntity =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(placeDTO), PlaceDTO.class);

        Assertions.assertEquals(HttpStatus.OK, updatedEntity.getStatusCode());
        Assertions.assertFalse(Objects.requireNonNull(updatedEntity.getBody()).getAvailable());
    }
}