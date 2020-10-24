package lv.sda.cinemaapi.service;

import lv.sda.cinemaapi.entity.Order;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.repository.OrderRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    private final OrderRepository repository = Mockito.mock(OrderRepository.class);
    private final OrderService service = new OrderService(repository);
    private final Session session = Mockito.mock(Session.class);

    @Test
    public void addOrder() {
        Order entity = new Order();
        entity.setGenerationTime(LocalDateTime.now());
        entity.setSession(session);
        entity.setTotalPrice(BigDecimal.ONE);
        entity.setPlaces("1");
        entity.setPersonalCode("05079011186");
        entity.setUser("Andrejs Krilovs");

        Order validOrder = service.addOrder(entity);
        verify(repository, times(1))
                .save(entity);
    }

    @Test
    public void addEmptyOrder() {
        Order emptyOrder = service.addOrder(new Order());
        verify(repository, times(0))
                .save(emptyOrder);
    }
}