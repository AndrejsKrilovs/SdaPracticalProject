package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OrderDTO {
    @NotBlank
    @JsonProperty(value = "name_surname")
    @Pattern(regexp = "[A-Z]{1}[a-z]{1,}\\s[A-Z]{1}[a-z]{1,}", message = "Incorrect Name and Surname")
    private String user;

    @NotBlank
    @JsonProperty(value = "personal_code")
    @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Incorrect Personal code")
    private String personalCode;

    @Positive
    private Long session;

    @NotBlank(message = "Should select at least one place")
    private String places;

    @JsonProperty(value = "generation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    @NotBlank(message = "Generated time should be with pattern 'dd.MM.yyyy hh:mm:ss'")
    private String generationTime;

    @JsonProperty(value = "total_price")
    @PositiveOrZero(message = "Total sum should be positive")
    private BigDecimal totalPrice;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public Long getSession() {
        return session;
    }

    public void setSession(Long session) {
        this.session = session;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(String generationTime) {
        this.generationTime = generationTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
