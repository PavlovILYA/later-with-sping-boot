CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS items (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT,
    url TEXT,
    description TEXT,
    created_time TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


DELETE FROM items;
DELETE FROM users;

ALTER TABLE items ALTER COLUMN id RESTART WITH 1;
ALTER TABLE users ALTER COLUMN id RESTART WITH 1;


-- DROP TABLE items;
-- DROP TABLE users;