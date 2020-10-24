package lv.sda.cinemaapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sess")
public class Session {
    @Id
    @SequenceGenerator(name="sess_id_seq", initialValue=10000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sess_id_seq")
    private Long id;

    private LocalDateTime dateTime;
    private Room room;
    private BigDecimal price;

    @ManyToOne(targetEntity = Film.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "film_id")
    private Film film;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
