package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.FilmDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@ActiveProfiles(profiles = "test")
@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmControllerTest {
    @LocalServerPort
    private Integer randomServerPort;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ENDPOINT_TO_TEST = "http://localhost:%d/api/film.svc%s";

    @Test
    @Sql(scripts = "/sql/film-data-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/film-data-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void findFilmsArrayEntity() {
        String urlToFindAllFilms = String.format(ENDPOINT_TO_TEST, randomServerPort, "/Films");
        ResponseEntity<FilmDTO[]> responseEntity = restTemplate.getForEntity(urlToFindAllFilms, FilmDTO[].class);

        Assert.assertFalse(Objects.isNull(responseEntity.getBody()));
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotEquals(0, Arrays.stream(responseEntity.getBody()).count());
    }

    @Test
    public void findEmptyFilmEntity() {
        String urlToFindAllFilms = String.format(ENDPOINT_TO_TEST, randomServerPort, "/Films");
        ResponseEntity<FilmDTO[]> responseEntity = restTemplate.getForEntity(urlToFindAllFilms, FilmDTO[].class);

        Assert.assertTrue(Objects.isNull(responseEntity.getBody()));
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}