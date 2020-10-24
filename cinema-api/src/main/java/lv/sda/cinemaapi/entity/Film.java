package lv.sda.cinemaapi.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Film {
    @Id
    @SequenceGenerator(name="film_id_seq", initialValue=10000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_seq")
    private Long id;

    private String title;
    private String picturePath;

    @Column(name = "f_length")
    private LocalTime length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
        this.length = length;
    }
}
