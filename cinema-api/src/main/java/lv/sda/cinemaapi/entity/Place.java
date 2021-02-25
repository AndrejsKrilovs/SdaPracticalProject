package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class Place {
    @EmbeddedId
    private PlacePrimaryKey id;

    @Column(name = "place_availability")
    private Boolean available;
}
