package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ResponseDTO<T> {
    @JsonProperty(value = "metadata")
    Metadata metadata;

    @JsonProperty(value = "content")
    List<T> entityList;
}
