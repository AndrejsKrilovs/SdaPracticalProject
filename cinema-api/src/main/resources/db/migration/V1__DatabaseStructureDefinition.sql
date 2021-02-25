    create table film (
       film_id bigint,
       film_length time,
       film_picture varchar(255),
       film_title varchar(255),
       constraint film_pk primary key (film_id)
    );

    create table ord (
       ord_id bigint,
       ord_time timestamp,
       ord_personal_code varchar(12),
       ord_places varchar(8),
       ord_price decimal(5,2),
       ord_user_name varchar(255),
       ord_session_id bigint,
       constraint ord_pk primary key (ord_id)
    );

    create table place (
       place_seat integer,
       place_room integer,
       place_availability boolean,
       constraint place_pk primary key (place_seat, place_room)
    );

    create table sess (
       sess_id bigint,
       sess_date_time timestamp,
       sess_price decimal(5,2),
       sess_room integer,
       sess_film_id bigint,
       constraint sess_pk primary key (sess_id)
    );
