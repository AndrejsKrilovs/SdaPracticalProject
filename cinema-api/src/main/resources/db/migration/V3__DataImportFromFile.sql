CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE('SA', 'FILM', './cinema-api/src/main/resources/db/data/film.dat', ';', '"', 'UTF-8', 0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE('SA', 'SESS', './cinema-api/src/main/resources/db/data/session.dat', ';', '"', 'UTF-8', 0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE('SA', 'PLACE', './cinema-api/src/main/resources/db/data/place.dat', ';', '"', 'UTF-8', 0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE('SA', 'ORD', './cinema-api/src/main/resources/db/data/order.dat', ';', '"', 'UTF-8', 0);