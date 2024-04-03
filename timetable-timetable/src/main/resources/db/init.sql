create schema timetable_microservice;
create role timetable_role;

grant connect on database pnu_system to timetable_role;
grant usage on schema timetable_microservice to timetable_role;
grant all on schema timetable_microservice to su_role;

create user timetable_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant timetable_role to timetable_user;

grant select, insert, delete, update on all tables in schema timetable_microservice to timetable_role;