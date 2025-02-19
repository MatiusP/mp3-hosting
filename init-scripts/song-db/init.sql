DROP TABLE IF EXISTS song_metadata;

CREATE TABLE song_metadata (
    id SERIAL PRIMARY KEY,
    album VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    year VARCHAR(255) NOT NULL
);