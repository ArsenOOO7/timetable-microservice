create schema group_microservice;
create role group_role;

grant
connect
on database pnu_system to group_role;
grant usage on schema
group_microservice to group_role;
grant all
on schema group_microservice to su_role;

create
user group_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant group_role to group_user;

grant
select,
insert
,
delete,
update
on all tables in schema group_microservice to group_role;