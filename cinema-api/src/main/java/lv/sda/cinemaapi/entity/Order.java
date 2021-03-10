package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ord")
public class Order {
    @Id
    @Column(name = "ord_id")
    @SequenceGenerator(name="ord_id_seq", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_id_seq")
    private Long id;

    @Column(name = "ord_user_name")
    private String user;

    @Column(name = "ord_personal_code")
    private String personalCode;

    @Column(name = "ord_places")
    private String places;

    @Column(name = "ord_time")
    private LocalDateTime generationTime;

    @Column(name = "ord_price")
    private BigDecimal totalPrice;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_session_id")
    private Session session;
}
