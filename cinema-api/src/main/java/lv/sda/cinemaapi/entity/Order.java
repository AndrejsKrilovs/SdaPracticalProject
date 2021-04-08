package lv.sda.cinemaapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @Column(name = "order_id")
    @SequenceGenerator(name="order_id_seq", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private Long id;

    @Column(name = "user_name", length = 20)
    private String user;

    @Column(name = "user_personal_code", length = 12)
    private String personalCode;

    @Column(name = "order_places", length = 10)
    private String places;

    @Column(name = "order_time")
    private LocalDateTime generationTime;

    @Column(name = "order_price", precision = 5, scale = 2)
    private BigDecimal totalPrice;

    @JoinColumn(name = "session_id")
    @ManyToOne(targetEntity = Session.class, fetch = FetchType.LAZY)
    private Session session;
}
