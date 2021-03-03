package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lv.sda.cinemaapi.config.JsonTimeDeserializer;

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
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime length;
}
