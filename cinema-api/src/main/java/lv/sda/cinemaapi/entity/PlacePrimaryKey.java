package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class PlacePrimaryKey implements Serializable {
    @Column(name = "room_number")
    private Integer room;

    @Column(name = "seat_number")
    private Integer place;
}
