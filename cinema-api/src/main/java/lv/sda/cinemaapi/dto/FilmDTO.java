package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lv.sda.cinemaapi.config.JsonTimeDeserializer;
import lv.sda.cinemaapi.config.JsonTimeSerializer;

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
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime length;
}
