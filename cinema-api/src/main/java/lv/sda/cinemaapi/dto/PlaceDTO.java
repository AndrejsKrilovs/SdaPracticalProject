package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class PlaceDTO {
    @JsonProperty(value = "room_number")
    private Integer roomNumber;

    @JsonProperty(value = "place_number")
    private Integer placeNumber;
    private Boolean available;
    private Boolean enabled;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlaceDTO.class.getSimpleName() + "[", "]")
                .add("roomNumber=" + roomNumber)
                .add("placeNumber=" + placeNumber)
                .add("available=" + available)
                .add("enabled=" + enabled)
                .toString();
    }
}
