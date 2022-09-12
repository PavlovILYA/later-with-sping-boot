CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    registration_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS items (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT,
    normal_url TEXT,
    resolved_url TEXT,
    mime_type VARCHAR(255),
    title TEXT,
    has_image BOOLEAN,
    has_video BOOLEAN,
    description TEXT,
    resolved_date TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS tags (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    item_id BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES items(id),
    CONSTRAINT unique_name UNIQUE (name)
);


DELETE FROM tags;
DELETE FROM items;
DELETE FROM users;

ALTER TABLE tags ALTER COLUMN id RESTART WITH 1;
ALTER TABLE items ALTER COLUMN id RESTART WITH 1;
ALTER TABLE users ALTER COLUMN id RESTART WITH 1;


-- DROP TABLE tags;
-- DROP TABLE items;
-- DROP TABLE users;