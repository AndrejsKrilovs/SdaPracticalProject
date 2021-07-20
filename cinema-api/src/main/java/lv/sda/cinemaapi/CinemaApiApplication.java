package lv.sda.cinemaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.repository.FilmRepository;
import lv.sda.cinemaapi.repository.PlaceRepository;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Cinema API", version = "2.0", description = "Cinema service application"))
public class CinemaApiApplication {
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;

    @PostConstruct
    public void init() {
//        if (filmRepository.count() == 0) {
//            for (long i = 1; i < 129; i++) {
//                Film film = new Film();
//                film.setTitle("Film title " + i);
//                film.setPicturePath("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg");
//
//                long minimalTime = LocalTime.of(0, 20).toNanoOfDay();
//                long maximalTime = LocalTime.of(2, 0).toNanoOfDay();
//                long generatedTime = ThreadLocalRandom.current().longs(minimalTime, maximalTime).limit(1).findFirst().orElse(0);
//                film.setLength(LocalTime.ofNanoOfDay(generatedTime));
//                filmRepository.save(film);
//            }
//        }
//
//        if (sessionRepository.count() == 0) {
//            for (long i = 1; i < 2048; i++) {
//                Session session = new Session();
//                session.setId(i);
//                session.setPrice(BigDecimal.valueOf(Math.random() * 3).setScale(2, RoundingMode.CEILING));
//
//                long filmId = ThreadLocalRandom.current().longs(10000, 10128).limit(1).findFirst().orElse(0);
//                filmRepository.findById(filmId).ifPresent(session::setFilm);
//
//                long minimalDate = LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC);
//                long maximalDate = LocalDateTime.now().plusMonths(3).toEpochSecond(ZoneOffset.UTC);
//                long generatedDate = ThreadLocalRandom.current().longs(minimalDate, maximalDate).limit(1).findFirst().orElse(0);
//                session.setDateTime(LocalDateTime.ofEpochSecond(generatedDate, 0, ZoneOffset.UTC));
//
//                sessionRepository.save(session);
//            }
//        }
//
//        if (placeRepository.count() == 0) {
//            for (long sessionId = 10001; sessionId < 12047; sessionId++) {
//                for (int placeNumber = 1; placeNumber <= 30; placeNumber++) {
//                    for (int roomNumber = 1; roomNumber < 5; roomNumber++) {
//
//                        PlacePrimaryKey placePrimaryKey = new PlacePrimaryKey();
//                        placePrimaryKey.setRoom(roomNumber);
//                        placePrimaryKey.setPlace(placeNumber);
//                        sessionRepository.findById(sessionId).ifPresent(placePrimaryKey::setSession);
//
//                        Place place = new Place();
//                        place.setId(placePrimaryKey);
//                        place.setAvailable(Boolean.TRUE);
//                        placeRepository.save(place);
//                    }
//                }
//            }
//        }
//
//        //sessionRepository.findSessionsByFilm(10036L, Pageable.ofSize(100)).forEach(System.out::println);
        placeRepository.findPlaceBySession(11745L, Pageable.ofSize(100)).forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }
}
