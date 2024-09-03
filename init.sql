create database pnu_system;
\connect pnu_system;

create schema liquibase;
create role su_role;
grant connect on database pnu_system to su_role;
grant all on schema liquibase to su_role;

create user liquibase_user nosuperuser nocreatedb nocreaterole login encrypted password '1234';
grant su_role to liquibase_user;