package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lv.sda.cinemaapi.entity.Room;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

@Data
public class SessionDTO {
    @JsonProperty(value = "session_id")
    private Long id;

    @JsonProperty(value = "date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime dateTime;

    @JsonProperty(value = "session_room")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Room room;

    @JsonProperty(value = "session_price")
    private BigDecimal price;

    @JsonProperty(value = "price_currency")
    private Currency currency = Currency.getInstance(Locale.getDefault());

    @JsonProperty(value = "session_film_id")
    private Long filmId;
}
