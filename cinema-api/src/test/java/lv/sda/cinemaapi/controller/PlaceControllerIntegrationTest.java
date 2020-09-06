package lv.sda.cinemaapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.cinemaapi.dto.PlaceDTO;
import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlaceService placeService;

    @Test
    void getPlacesByRoomNumber() throws JsonProcessingException {
        String result = restTemplate.getForObject("http://localhost:" + port
                + "/api/place.svc/Places(4)", String.class);

        JsonNode jsonNode = objectMapper.readTree(result);
        List<PlaceDTO> placeDTOS = objectMapper.convertValue(jsonNode, new TypeReference<List<PlaceDTO>>() {});

        assertEquals(40, placeDTOS.size());
        assertEquals(1, placeDTOS.get(0).getPlaceNumber());
        assertEquals(Boolean.FALSE, placeDTOS.get(1).getAvailable());
        assertEquals(Boolean.FALSE, placeDTOS.get(2).getEnabled());
    }

    @Test
    void updatePlace() {
        PlaceDTO placeDTO = new PlaceDTO();

        placeDTO.setAvailable(Boolean.TRUE);
        placeDTO.setRoomNumber(3);
        placeDTO.setPlaceNumber(20);

        restTemplate.put("http://localhost:" + port + "/api/place.svc/Place", placeDTO, PlaceDTO.class);

        List<Place> places = placeService.placesByRoomNumber(3);
        System.out.println(places);

        assertEquals(Boolean.TRUE, places.get(19).getAvailable());
    }
}