CREATE TABLE goods (
    id SERIAL PRIMARY KEY,
    name varchar(50) NOT NULL,
    price int NOT NULL,
    dateTimeAdded DATE
);

CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    sole VARCHAR(50) NOT NULL
);