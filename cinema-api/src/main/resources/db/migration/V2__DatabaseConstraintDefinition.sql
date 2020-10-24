    alter table sess
       add constraint film_session_fk
       foreign key (film_id)
       references film;

    alter table ord
       add constraint session_order_fk
       foreign key (session_id)
       references sess;
