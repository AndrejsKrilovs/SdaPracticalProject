package lv.sda.cinemaapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.cinemaapi.dto.SessionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSevenSessionsByFilm() throws JsonProcessingException {
        String result = restTemplate.getForObject("http://localhost:" + port
                + "/api/session.svc/Sessions(50)", String.class);

        JsonNode jsonNode = objectMapper.readTree(result);
        List<SessionDTO> sessionDTOS = objectMapper.convertValue(jsonNode, new TypeReference<List<SessionDTO>>() {});

        assertEquals(7, sessionDTOS.size());
        assertEquals(50, sessionDTOS.get(0).getFilmId());
    }
}