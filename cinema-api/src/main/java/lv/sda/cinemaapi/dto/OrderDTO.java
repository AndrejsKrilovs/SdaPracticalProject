package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Value
@Builder
public class OrderDTO {
    @JsonProperty(value = "name_surname")
    String user;

    @JsonProperty(value = "personal_code")
    String personalCode;

    @JsonProperty(value = "film_name")
    String filmName;

    @JsonProperty(value = "session_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime sessionDate;

    @JsonProperty(value = "total_price")
    BigDecimal totalPrice;

    @JsonProperty(value = "price_currency")
    Currency currency;

    @JsonProperty(value = "places")
    String places;

    @JsonProperty(value = "order_generated_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime orderGeneratedDate = LocalDateTime.now();
}
