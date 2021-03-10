package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Metadata {
    @JsonProperty(value = "offset")
    private Long offset;

    @JsonProperty(value = "page_number")
    private Integer pageNumber;

    @JsonProperty(value = "total_pages")
    private Integer totalPages;

    @JsonProperty(value = "total_elements")
    private Long totalElements;
}
