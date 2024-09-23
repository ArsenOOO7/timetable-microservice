revoke connect on database pnu_system from identity_access_role;

drop schema identity_access_microservice cascade;

drop table liquibase.dbchangelog_identity_access_ms cascade;

drop table liquibase.dbchangeloglock_identity_access_ms cascade;

DROP role identity_access_role;
DROP role identity_access_user;