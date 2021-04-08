create table film (
    film_id bigint,
    film_length time,
    film_picture varchar(255),
    film_title varchar(20),
    constraint film_pk primary key (film_id)
);

create table session_table (
    session_id bigint,
    session_date_time timestamp,
    session_price decimal(5,2),
    film_id bigint,
    constraint session_pk primary key (session_id),
    constraint film_session_fk foreign key (film_id) references film
);

create table place (
    seat_number integer,
    room_number integer,
    session_id bigint,
    place_availability boolean,
    constraint place_pk primary key (seat_number, room_number, session_id),
    constraint session_place_fk foreign key (session_id) references session_table
);

create table order_table (
    order_id bigint,
    order_time timestamp,
    order_places varchar(10),
    order_price decimal(5,2),
    user_personal_code varchar(12),
    user_name varchar(20),
    session_id bigint,
    constraint order_pk primary key (order_id),
    constraint order_session_fk foreign key (session_id) references session_table
);