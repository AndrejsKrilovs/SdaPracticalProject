package lv.sda.cinemaapi.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
public class Film {
    @Id
    @Column(name = "film_id")
    @SequenceGenerator(name="film_id_seq", initialValue=10000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_seq")
    private Long id;

    @Column(name = "film_title")
    private String title;

    @Column(name = "film_picture")
    private String picturePath;

    @Column(name = "film_length")
    private LocalTime length;
}
