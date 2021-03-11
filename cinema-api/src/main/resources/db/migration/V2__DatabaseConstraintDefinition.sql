    alter table session_table
       add constraint film_session_fk
       foreign key (session_film_id)
       references film;

    alter table ord
       add constraint order_session_fk
       foreign key (ord_session_id)
       references session_table;
