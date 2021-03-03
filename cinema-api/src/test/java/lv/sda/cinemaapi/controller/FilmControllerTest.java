package lv.sda.cinemaapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import lv.sda.cinemaapi.dto.FilmDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void initTest() throws IOException {
        @Cleanup
        InputStream inputStream = getClass().getResourceAsStream("/film-list.json");
        ObjectMapper mapper = new ObjectMapper();
        List<FilmDTO> film = mapper.readValue(inputStream, new TypeReference<>(){});
        log.info(String.valueOf(film));
    }
}