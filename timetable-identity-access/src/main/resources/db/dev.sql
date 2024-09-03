CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO identity_access_microservice."user" (id, first_name, last_name, email, password, type, version)
VALUES (uuid_generate_v4(), 'user', 'lastname', 'test@gmail.com', '1234', 'ADMIN', 1);


INSERT INTO identity_access_microservice.role (id, name, type, version)
VALUES (uuid_generate_v4(), 'test_role', 'ADMIN', 1);

INSERT INTO identity_access_microservice.permission (id, name, "group", type)
VALUES (uuid_generate_v4(), 'ROLE_CREATE', 'HZ', 'ADMIN'),
       (uuid_generate_v4(), 'ROLE_EDIT', 'HZ', 'ADMIN');
