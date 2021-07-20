package lv.sda.cinemaapi.repository;

import lv.sda.cinemaapi.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = "select s from Session s where s.film.id = :film")
    Page<Session> findSessionsByFilm(@Param(value = "film") Long film, Pageable pageable);
}
