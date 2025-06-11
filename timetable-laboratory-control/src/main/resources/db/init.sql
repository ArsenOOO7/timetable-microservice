create schema laboratory_control_microservice;
create role laboratory_control_role;

grant connect on database pnu_system to laboratory_control_role;
grant usage on schema laboratory_control_microservice to laboratory_control_role;
grant all on schema laboratory_control_microservice to su_role;

create user laboratory_control_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant laboratory_control_role to laboratory_control_user;

grant select, insert, delete, update on all tables in schema laboratory_control_microservice to laboratory_control_role;
alter default privileges in schema laboratory_control_microservice grant select, insert, update, delete on tables to laboratory_control_role;