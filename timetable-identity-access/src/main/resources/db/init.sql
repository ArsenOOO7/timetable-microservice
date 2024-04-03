create schema timetable_identity_access;
create role timetable_identity_access_role;

grant connect on database pnu_system to timetable_identity_access_role;
grant usage on schema timetable_identity_access to timetable_identity_access_role;
grant all on schema timetable_identity_access to su_role;

create user timetable_identity_access_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant timetable_identity_access_user to timetable_identity_access_role;

grant select, insert, delete, update on all tables in schema timetable_identity_access to timetable_identity_access_role;