CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    name         TEXT         NOT NULL,
    email        VARCHAR(255) NOT NULL,
    age          INTEGER      NOT NULL,
    phone_number TEXT         NOT NULL,
    created_time TIMESTAMP,
    last_updated TIMESTAMP
);
