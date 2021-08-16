package lv.sda.cinemaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.FilmRepository;
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
@OpenAPIDefinition(info = @Info(title = "Cinema API", version = "2.0", description = "Cinema service application"))
public class CinemaApiApplication {
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;

    @PostConstruct
    public void init() {
        if (filmRepository.count() == 0) {
            for (long i = 1; i <= 130; i++) {
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
            for (long i = 1; i <= 2000; i++) {
                Session session = new Session();
                session.setId(i);
                session.setPrice(BigDecimal.valueOf(Math.random() * 3).setScale(2, RoundingMode.CEILING));

                long filmId = ThreadLocalRandom.current().longs(10000, 10128).limit(1).findFirst().orElse(0);
                filmRepository.findById(filmId).ifPresent(session::setFilm);

                long minimalDate = LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC);
                long maximalDate = LocalDateTime.now().plusMonths(3).toEpochSecond(ZoneOffset.UTC);
                long generatedDate = ThreadLocalRandom.current().longs(minimalDate, maximalDate).limit(1).findFirst().orElse(0);
                session.setDateTime(LocalDateTime.ofEpochSecond(generatedDate, 0, ZoneOffset.UTC));

                int roomNumber = ThreadLocalRandom.current().ints(1, 5).limit(1).findFirst().orElse(0);
                session.setRoomNumber(roomNumber);

                sessionRepository.save(session);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }
}
