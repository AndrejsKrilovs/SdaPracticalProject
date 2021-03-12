package lv.sda.cinemaapi;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.*;
import lv.sda.cinemaapi.repository.FilmRepository;
import lv.sda.cinemaapi.repository.PlaceRepository;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaApiApplication {
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;

    @PostConstruct
    public void init() {
        if (filmRepository.count() == 0) {
            for (long i = 1; i < 129; i++) {
                Film film = new Film();
                film.setTitle("Film title " + i);
                film.setPicturePath("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg");

                long minimalTime = LocalTime.of(0, 20).toNanoOfDay();
                long maximalTime = LocalTime.of(2, 0).toNanoOfDay();
                long generatedTime = ThreadLocalRandom.current().longs(minimalTime, maximalTime).limit(1).findFirst().orElse(0);
                film.setLength(LocalTime.ofNanoOfDay(generatedTime));
                filmRepository.save(film);
            }
        }

        if (sessionRepository.count() == 0) {
            for (long i = 1; i < 2048; i++) {
                Session session = new Session();
                session.setId(i);
                session.setPrice(BigDecimal.valueOf(Math.random() * 3).setScale(2, RoundingMode.CEILING));

                long filmId = ThreadLocalRandom.current().longs(10000, 10128).limit(1).findFirst().orElse(0);
                session.setFilm(filmRepository.getOne(filmId));

                long minimalDate = LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC);
                long maximalDate = LocalDateTime.now().plusMonths(3).toEpochSecond(ZoneOffset.UTC);
                long generatedDate = ThreadLocalRandom.current().longs(minimalDate, maximalDate).limit(1).findFirst().orElse(0);
                session.setDateTime(LocalDateTime.ofEpochSecond(generatedDate, 0, ZoneOffset.UTC));

                int room = BigDecimal.valueOf(Math.random() * Room.values().length).intValue();
                session.setRoom(Room.values()[room]);
                sessionRepository.save(session);
            }
        }

        if (placeRepository.count() == 0) {
            for (long sessionId = 10000; sessionId < 12047; sessionId++) {
                for (int placeNumber = 1; placeNumber <= 30; placeNumber++) {
                    PlacePrimaryKey placePrimaryKey = new PlacePrimaryKey();
                    placePrimaryKey.setPlace(placeNumber);
                    placePrimaryKey.setSession(sessionRepository.getOne(sessionId));

                    Place place = new Place();
                    place.setId(placePrimaryKey);
                    place.setAvailable(Boolean.TRUE);
                    placeRepository.save(place);
                }
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }
}
