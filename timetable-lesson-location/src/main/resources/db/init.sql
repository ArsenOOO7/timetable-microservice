create schema timetable_lesson_location;
create role timetable_lesson_location_role;

grant connect on database pnu_system to timetable_lesson_location_role;
grant usage on schema timetable_lesson_location to timetable_lesson_location_role;
grant all on schema timetable_lesson_location to su_role;

create user timetable_lesson_location_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant timetable_lesson_location_user to timetable_lesson_location_role;

grant select, insert, delete, update on all tables in schema timetable_lesson_location to timetable_lesson_location_role;