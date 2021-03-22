package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class FilmDTO {
    @JsonProperty(value = "film_id")
    Long id;

    @JsonProperty(value = "film_title")
    String title;

    @JsonProperty(value = "film_picture")
    String picturePath;

    @JsonProperty(value = "film_length")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime length;
}
