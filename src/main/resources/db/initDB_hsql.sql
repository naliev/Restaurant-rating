DROP TABLE dish IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;

DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT uk_restaurant UNIQUE (name)
);

CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    user_id       INTEGER            NOT NULL,
    date          DATE DEFAULT NOW() NOT NULL,
    restaurant_id INTEGER            NOT NULL,

    CONSTRAINT uk_vote UNIQUE (user_id, date),
    CONSTRAINT fk_vote_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_vote_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    restaurant_id INTEGER      NOT NULL,
    name          VARCHAR(100) NOT NULL,
    price         INTEGER      NOT NULL,
    CONSTRAINT fk_dish_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) On DELETE CASCADE
);
