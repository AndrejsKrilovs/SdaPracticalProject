package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class PreOrderDTO {
    @JsonProperty(value = "film")
    Long filmNumber;

    @JsonProperty(value = "session")
    Long sessionNumber;

    @JsonProperty(value = "places")
    Set<Integer> places;
}
