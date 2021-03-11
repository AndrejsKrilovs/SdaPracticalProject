package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class FilmDTO {
    @JsonProperty(value = "film_id")
    private Long id;

    @JsonProperty(value = "film_title")
    private String title;

    @JsonProperty(value = "film_picture")
    private String picturePath;

    @JsonProperty(value = "film_length")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime length;
}
