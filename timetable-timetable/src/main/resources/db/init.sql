create schema timetable_timetable;
create role timetable_timetable_role;

grant connect on database pnu_system to timetable_timetable_role;
grant usage on schema timetable_timetable to timetable_timetable_role;
grant all on schema timetable_timetable to su_role;

create user timetable_timetable_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant timetable_timetable_role to timetable_timetable_user;

grant select, insert, delete, update on all tables in schema timetable_timetable to timetable_timetable_role;