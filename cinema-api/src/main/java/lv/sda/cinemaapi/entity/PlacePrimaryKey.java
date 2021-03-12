package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class PlacePrimaryKey implements Serializable {
    @Column(name = "place_seat")
    private Integer place;

    @JoinColumn(name = "place_session_id")
    @ManyToOne(targetEntity = Session.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Session session;
}
