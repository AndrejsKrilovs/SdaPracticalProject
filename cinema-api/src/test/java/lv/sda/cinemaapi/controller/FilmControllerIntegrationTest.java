package lv.sda.cinemaapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.cinemaapi.dto.FilmDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTenFilms() throws JsonProcessingException {
        String result = restTemplate.getForObject("http://localhost:" + port
                + "/api/film.svc/Films?offset=1", String.class);

        JsonNode jsonNode = objectMapper.readTree(result);
        List<FilmDTO> filmDTOS = objectMapper.convertValue(jsonNode, new TypeReference<List<FilmDTO>>() {});

        assertEquals(10, filmDTOS.size());

        for (int i=0; i<10; i++) {
            assertEquals("Title " + (i + 10), filmDTOS.get(i).getTitle());
        }
        assertEquals("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg",
                filmDTOS.get(0).getPicturePath());
    }

    @Test
    void getFilmById() {
        FilmDTO result = restTemplate.getForObject("http://localhost:" + port
                + "/api/film.svc/Film(5)", FilmDTO.class);

        assertEquals("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg", result.getPicturePath());
        assertEquals("Title 4", result.getTitle());
        assertEquals(5L, result.getId());
    }
}