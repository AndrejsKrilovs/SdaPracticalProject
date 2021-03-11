package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Response<T> {
    @JsonProperty(value = "metadata")
    private Metadata metadata;

    @JsonProperty(value = "content")
    private List<T> entityList;
}
