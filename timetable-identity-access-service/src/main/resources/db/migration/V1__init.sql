CREATE TABLE IF NOT EXISTS users(
    id bigserial primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(56) not null
);

CREATE TABLE IF NOT EXISTS access_tokens(
    id bigint primary key not null,
    token varchar(255) not null
);

ALTER TABLE access_tokens ADD CONSTRAINT fk_user_token FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE;