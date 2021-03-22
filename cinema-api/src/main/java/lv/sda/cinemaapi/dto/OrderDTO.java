package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class OrderDTO {
    @JsonProperty(value = "name_surname")
    @NotBlank(message = "Name should not be empty")
    @Pattern(message = "Incorrect name and Surname",
            regexp = "[A-Z\\u0410-\\u042F]{1}[a-z\\u0430-\\u044F]{1,}\\s[A-Z\\u0410-\\u042F]{1}[a-z\\u0430-\\u044F]{1,}")
    String user;

    @JsonProperty(value = "personal_code")
    @NotBlank(message = "Personal code should not be empty")
    @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Incorrect Personal code")
    String personalCode;

    @NotBlank(message = "Session number should not be empty")
    @Positive(message = "Session number should be positive")
    Long session;

    @NotBlank(message = "Should select at least one place")
    String places;

    @JsonProperty(value = "generation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    @NotBlank(message = "Generated time should be with pattern 'dd.MM.yyyy hh:mm:ss'")
    LocalDateTime generationTime;

    @JsonProperty(value = "total_price")
    @PositiveOrZero(message = "Total sum should be positive")
    BigDecimal totalPrice;
}
