package lv.sda.cinemaapi;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.repository.FilmRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaApiApplication {
    private final FilmRepository filmRepository;

    @PostConstruct
    public void init() {
        if (filmRepository.count() == 0) {
            for (long i = 1; i < 129; i++) {
                Film film = new Film();
                film.setTitle("Film title " + i);
                film.setPicturePath("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg");

                long minimalTime = LocalTime.MIN.getLong(ChronoField.NANO_OF_DAY);
                long maximalTime = LocalTime.of(2, 0).getLong(ChronoField.NANO_OF_DAY);
                long generatedTime = ThreadLocalRandom.current().longs(minimalTime, maximalTime).limit(1).findFirst().orElse(0);
                film.setLength(LocalTime.ofNanoOfDay(generatedTime));
                filmRepository.save(film);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }
}
