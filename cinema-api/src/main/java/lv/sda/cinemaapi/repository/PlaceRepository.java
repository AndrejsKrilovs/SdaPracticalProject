package lv.sda.cinemaapi.repository;

import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, PlacePrimaryKey> {
//    @Query(value = "from Place p join p.id.session s where s.id = :session")
//    Page<Place> findPlaceBySession(@Param(value = "session") Long sessionId, Pageable pageable);
}
