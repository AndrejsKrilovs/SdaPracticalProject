package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.FilmDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmControllerIntegrationTest {

    private final static String URL_TO_TEST = "http://localhost:%d/api/film.svc%s";
    private final static Integer ITEMS_PER_PAGE = 10;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void filmListWithoutOffsetTest() {
        String url = String.format(URL_TO_TEST, port, "/Films");
        ResponseEntity<FilmDTO[]> entity = restTemplate.getForEntity(url, FilmDTO[].class);

        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(ITEMS_PER_PAGE, Objects.requireNonNull(entity.getBody()).length);
    }

    @Test
    public void filmListWithOffsetTest() {
        String url = String.format(URL_TO_TEST, port, "/Films");
        URI generatedRequest = UriComponentsBuilder.fromUriString(url)
                .queryParam("offset", "1")
                .build().toUri();

        ResponseEntity<FilmDTO[]> entity = restTemplate.getForEntity(generatedRequest, FilmDTO[].class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(ITEMS_PER_PAGE, Objects.requireNonNull(entity.getBody()).length);
    }

    @Test
    public void filmListWithLongOffsetTest() {
        String url = String.format(URL_TO_TEST, port, "/Films");
        URI generatedRequest = UriComponentsBuilder.fromUriString(url)
                .queryParam("offset", "1024")
                .build().toUri();

        ResponseEntity<FilmDTO[]> entity = restTemplate.getForEntity(generatedRequest, FilmDTO[].class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, entity.getStatusCode());
    }

    @Test
    public void getOneFilmTest() {
        String url = String.format(URL_TO_TEST, port, "/Film(1)");
        ResponseEntity<FilmDTO> entity = restTemplate.getForEntity(url, FilmDTO.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(entity.getBody()).getId());
    }

    @Test
    public void getOneFilmWithIncorrectParameterTest() {
        String url = String.format(URL_TO_TEST, port, "/Film(1024)");
        ResponseEntity<FilmDTO> entity = restTemplate.getForEntity(url, FilmDTO.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
    }

    @Test
    public void filterFilmTest() {
        String url = String.format(URL_TO_TEST, port, "/Films$filter?offset=0");
        ResponseEntity<FilmDTO[]> entity = restTemplate.getForEntity(url, FilmDTO[].class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(ITEMS_PER_PAGE, Objects.requireNonNull(entity.getBody()).length);
    }

    @Test
    public void filterFilmWithIncorrectParameterTest() {
        String url = String.format(URL_TO_TEST, port, "/Films$filter?offset=999&title=q");
        ResponseEntity<FilmDTO[]> entity = restTemplate.getForEntity(url, FilmDTO[].class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, entity.getStatusCode());
    }
}