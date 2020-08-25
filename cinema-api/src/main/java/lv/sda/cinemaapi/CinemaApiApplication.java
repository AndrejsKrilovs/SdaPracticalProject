package lv.sda.cinemaapi;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.FilmRepository;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaApiApplication {
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        filmRepository.deleteAll();
        sessionRepository.deleteAll();

        for (int i = 0; i < 128; i++) {
            Film entity = new Film();
            entity.setLength(LocalTime.of((int) (Math.round(Math.random() * 2) + 1), (int) (Math.round(Math.random() * 59))));
            entity.setPicturePath("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg");
            entity.setTitle(String.format("Title %d", i));
            filmRepository.save(entity);
        }

        for (int i = 1; i < 2048; i++) {
            Session entity = new Session();
            entity.setDateTime(LocalDateTime.now());
            entity.setRoom((byte) Math.round(Math.random()* 16));
            entity.setPrice(BigDecimal.valueOf(Math.random() * 10));

            Film film = new Film();
            film.setId(Math.round(Math.random()* 127 + 1));
            entity.setFilm(film);
            sessionRepository.save(entity);
        }
    }
}
