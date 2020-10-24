package lv.sda.cinemaapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ord")
public class Order {
    @Id
    @SequenceGenerator(name="ord_id_seq", initialValue=10000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_id_seq")
    private Long id;

    @Column(name = "usr")
    private String user;
    private String personalCode;
    private String places;
    private LocalDateTime generationTime;
    private BigDecimal totalPrice;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

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

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public LocalDateTime getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(LocalDateTime generationTime) {
        this.generationTime = generationTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
