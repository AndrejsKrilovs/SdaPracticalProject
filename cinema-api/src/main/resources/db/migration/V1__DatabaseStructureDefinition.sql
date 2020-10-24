 create table film (
       id bigint,
       f_length time,
       picture_path varchar(255),
       title varchar(255),
       primary key (id)
    );

    create table ord (
       id bigint,
        generation_time timestamp,
        personal_code varchar(255),
        places varchar(255),
        total_price decimal(19,2),
        usr varchar(255),
        session_id bigint,
        primary key (id)
    );

    create table place (
       place_number integer,
        room_number integer,
        available boolean,
        primary key (place_number, room_number)
    );

    create table sess (
       id bigint,
        date_time timestamp,
        price decimal(19,2),
        room integer,
        film_id bigint,
        primary key (id)
    );
