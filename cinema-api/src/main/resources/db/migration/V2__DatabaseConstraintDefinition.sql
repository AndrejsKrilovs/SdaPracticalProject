    alter table sess
       add constraint film_session_fk
       foreign key (sess_film_id)
       references film;

    alter table ord
       add constraint order_session_fk
       foreign key (ord_session_id)
       references sess;
