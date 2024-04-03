create schema lesson_location_microservice;
create role lesson_location_role;

grant connect on database pnu_system to lesson_location_role;
grant usage on schema lesson_location_microservice to lesson_location_role;
grant all on schema lesson_location_microservice to su_role;

create user lesson_location_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant lesson_location_role to lesson_location_user;

grant select, insert, delete, update on all tables in schema lesson_location_microservice to lesson_location_role;