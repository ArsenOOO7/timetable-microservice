create schema identity_access_microservice;
create role identity_access_role;

grant
connect
on database pnu_system to identity_access_role;
grant usage on schema
identity_access_microservice to identity_access_role;
grant all
on schema identity_access_microservice to su_role;

create
user identity_access_user nosuperuser nocreatedb nocreaterole login encrypted password '12345';
grant identity_access_role to identity_access_user;

grant
select,
insert
,
delete
,
update
    on all tables in schema identity_access_microservice to identity_access_role;