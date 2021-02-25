package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sess")
public class Session {
    @Id
    @Column(name = "sess_id")
    @SequenceGenerator(name="sess_id_seq", initialValue=10000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sess_id_seq")
    private Long id;

    @Column(name = "sess_date_time")
    private LocalDateTime dateTime;

    @Column(name = "sess_room")
    private Room room;

    @Column(name = "sess_price")
    private BigDecimal price;

    @ManyToOne(targetEntity = Film.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sess_film_id")
    private Film film;
}
