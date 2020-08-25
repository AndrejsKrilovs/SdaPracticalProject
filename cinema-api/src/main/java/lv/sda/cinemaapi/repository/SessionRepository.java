package lv.sda.cinemaapi.repository;

import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByFilm(Film film, Pageable pageable);
}
