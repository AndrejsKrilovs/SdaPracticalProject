package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "session_table")
public class Session {
    @Id
    @Column(name = "session_id")
    @SequenceGenerator(name="session_id_seq", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id_seq")
    private Long id;

    @Column(name = "session_date_time")
    private LocalDateTime dateTime;

    @Column(name = "session_price", precision = 5, scale = 2)
    private BigDecimal price;

    @JoinColumn(name = "film_id")
    @ManyToOne(targetEntity = Film.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Film film;

    @Column(name = "room_number")
    private Integer roomNumber;
}
