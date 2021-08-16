package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place_table")
public class Place {
    @EmbeddedId
    private PlacePrimaryKey id;

    @Column(name = "place_availability")
    private Boolean available;
}
