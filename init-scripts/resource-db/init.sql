DROP TABLE IF EXISTS resource_entity;

CREATE TABLE resource_entity (
    id SERIAL PRIMARY KEY,
    content OID NOT NULL
);