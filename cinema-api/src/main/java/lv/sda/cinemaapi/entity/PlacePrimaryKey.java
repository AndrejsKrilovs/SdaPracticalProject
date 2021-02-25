package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PlacePrimaryKey implements Serializable {
    @Column(name = "place_room")
    private Room room;

    @Column(name = "place_seat")
    private Integer place;
}
