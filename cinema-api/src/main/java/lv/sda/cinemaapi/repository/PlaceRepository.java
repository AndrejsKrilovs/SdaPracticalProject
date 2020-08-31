package lv.sda.cinemaapi.repository;

import lv.sda.cinemaapi.entity.Place;
import lv.sda.cinemaapi.entity.PlacePrimaryKey;
import lv.sda.cinemaapi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, PlacePrimaryKey> {
    @Query("select p from Place p where p.id.roomNumber = :room")
    List<Place> findPlaceByRoom(@Param("room") Room room);
}
