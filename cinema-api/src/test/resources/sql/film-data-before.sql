delete from sa.film;

insert into sa.film (film_id, film_title, film_picture, film_length)
    values(1000, 'Test film 1', '/path/to/image/1', time('00:00'));
insert into sa.film (film_id, film_title, film_picture, film_length)
    values(10001, 'Test film 2', '/path/to/image/2', time('00:00'));
insert into sa.film (film_id, film_title, film_picture, film_length)
    values(10002, 'Test film 3', '/path/to/image/3', time('00:00'));