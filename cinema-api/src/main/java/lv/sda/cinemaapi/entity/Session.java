package lv.sda.cinemaapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public Byte getRoom() {
        return room;
    }

    public void setRoom(Byte room) {
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
