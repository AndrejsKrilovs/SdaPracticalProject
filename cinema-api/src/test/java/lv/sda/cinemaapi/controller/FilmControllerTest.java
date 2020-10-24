package lv.sda.cinemaapi.controller;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = {"/db/init-films.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/db/invalidate-films.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FilmControllerTest extends AbstractTestController{

    @Test
    public void findAll() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void findAllByTitle() {
    }
}