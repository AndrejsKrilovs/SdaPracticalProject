package lv.sda.cinemaapi.controller;

import lv.sda.cinemaapi.dto.SessionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionControllerIntegrationTest {

    private final static String URL_TO_TEST = "http://localhost:%d/api/film.svc%s";
    private final static Integer ITEMS_PER_PAGE = 7;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findSessionByFilmTest() {
        String url = String.format(URL_TO_TEST, port, "/Sessions(1)");
        ResponseEntity<SessionDTO[]> entity = restTemplate.getForEntity(url, SessionDTO[].class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
//
//    @Test
//    public void findSessionByFilmWithIncorrectParameterTest() {
//        String url = String.format(URL_TO_TEST, port, "/Sessions(99999)");
//        ResponseEntity<SessionDTO[]> entity = restTemplate.getForEntity(url, SessionDTO[].class);
//        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
//        Assertions.assertArrayEquals(new SessionDTO[]{new SessionDTO()}, entity.getBody());
//    }
}