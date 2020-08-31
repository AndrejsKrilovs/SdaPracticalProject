package lv.sda.cinemaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceDTO {
    @JsonProperty(value = "room_number")
    private Byte roomNumber;

    @JsonProperty(value = "place_number")
    private Byte placeNumber;
    private Boolean available;

    public Byte getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Byte roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Byte getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Byte placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
