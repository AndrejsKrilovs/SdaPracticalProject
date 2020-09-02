package lv.sda.cinemaapi.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Place {
    @EmbeddedId
    private PlacePrimaryKey id;
    private Boolean available;

    public PlacePrimaryKey getId() {
        return id;
    }

    public void setId(PlacePrimaryKey id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", available=" + available +
                '}';
    }
}
