create schema academic_catalog_microservice;
create role academic_catalog_role;

grant connect on database pnu_system to academic_catalog_role;
grant usage on schema academic_catalog_microservice to academic_catalog_role;
grant all on schema academic_catalog_microservice to su_role;

create user academic_catalog_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant academic_catalog_role to academic_catalog_user;

grant select, insert, delete, update on all tables in schema academic_catalog_microservice to academic_catalog_role;
alter default privileges in schema academic_catalog_microservice grant select, insert, update, delete on tables to academic_catalog_role;