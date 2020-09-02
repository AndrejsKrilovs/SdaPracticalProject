package lv.sda.cinemaapi;

import lv.sda.cinemaapi.entity.*;
import lv.sda.cinemaapi.repository.FilmRepository;
import lv.sda.cinemaapi.repository.PlaceRepository;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class CinemaApiApplication {
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;

    public CinemaApiApplication(FilmRepository filmRepository, SessionRepository sessionRepository, PlaceRepository placeRepository) {
        this.filmRepository = filmRepository;
        this.sessionRepository = sessionRepository;
        this.placeRepository = placeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        placeRepository.deleteAll();
        sessionRepository.deleteAll();
        filmRepository.deleteAll();

        if(filmRepository.count() == 0) {
            for (int i = 0; i < 128; i++) {
                Film entity = new Film();
                entity.setLength(LocalTime.of((int) (Math.round(Math.random() * 2) + 1), (int) (Math.round(Math.random() * 59))));
                entity.setPicturePath("https://miro.medium.com/max/1200/0*jSJUA3vYRpJA3oK3.jpg");
                entity.setTitle(String.format("Title %d", i));
                filmRepository.save(entity);
            }
        }

        if(sessionRepository.count() == 0) {
            for (int i = 1; i < 2048; i++) {
                Session entity = new Session();
                entity.setDateTime(LocalDateTime.now());

                int room = (int) (Math.round(Math.random() * 3) + 1);
                entity.setRoom(Room.values()[room]);
                entity.setPrice(BigDecimal.valueOf(Math.random() * 10));

                Film film = new Film();
                film.setId(Math.round(Math.random()* 127 + 1));
                entity.setFilm(film);
                sessionRepository.save(entity);
            }
        }

        if(placeRepository.count() == 0) {
            for (int i = 1; i <= 4 ; i++) {
                for (int j = 1; j <= 40; j++) {
                    Place place = new Place();
                    place.setAvailable(Boolean.FALSE);

                    PlacePrimaryKey id = new PlacePrimaryKey();
                    id.setRoomNumber(Room.values()[i]);
                    id.setPlaceNumber(j);
                    place.setId(id);
                    placeRepository.save(place);
                }
            }
        }
    }
}
