package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Byte room;
    private BigDecimal price;

    @ManyToOne(targetEntity = Film.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "film_session_fk"))
    private Film film;
}
