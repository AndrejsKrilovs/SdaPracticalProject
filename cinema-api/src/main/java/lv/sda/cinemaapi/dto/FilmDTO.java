package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FilmDTO {
    private Long id;
    private String title;

    @JsonProperty(value = "picture")
    private String picturePath;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private String length;
}
