package lv.sda.cinemaapi.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlacePrimaryKey implements Serializable {
    private Room roomNumber;
    private Byte placeNumber;

    public Room getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Byte getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Byte placeNumber) {
        this.placeNumber = placeNumber;
    }
}
