package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Metadata {
    @JsonProperty(value = "offset")
    Long offset;

    @JsonProperty(value = "page_number")
    Integer pageNumber;

    @JsonProperty(value = "total_pages")
    Integer totalPages;

    @JsonProperty(value = "total_elements")
    Long totalElements;
}
