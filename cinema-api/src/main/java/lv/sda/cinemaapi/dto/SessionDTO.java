package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SessionDTO {
    private Long id;

    @JsonProperty(value = "date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm")
    private String dateTime;
    private Byte room;
    private BigDecimal price;

    @JsonProperty(value = "film_id")
    private Long filmId;
}
