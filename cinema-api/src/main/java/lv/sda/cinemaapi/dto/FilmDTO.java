package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class FilmDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "picture")
    private String picturePath;

    @JsonProperty(value = "length")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private LocalTime length;
}
