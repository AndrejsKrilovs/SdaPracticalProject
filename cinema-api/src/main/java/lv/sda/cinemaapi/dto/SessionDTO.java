package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

@Value
@Builder
public class SessionDTO {
    @JsonProperty(value = "session_id")
    Long id;

    @JsonProperty(value = "date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime dateTime;

    @JsonProperty(value = "session_room")
    Integer roomNumber;

    @JsonProperty(value = "session_price")
    BigDecimal price;

    @JsonProperty(value = "price_currency")
    Currency currency = Currency.getInstance(Locale.getDefault());
}
