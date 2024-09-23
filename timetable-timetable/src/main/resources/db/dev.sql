revoke connect on database pnu_system from lesson_location_role;
drop schema lesson_location_microservice cascade;

drop table liquibase.dbchangelog_lesson_location_ms cascade;

drop table liquibase.dbchangeloglock_lesson_location_ms cascade;

DROP role lesson_location_role;
DROP role lesson_location_user;