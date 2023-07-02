create TABLE goods
(
    id SERIAL PRIMARY KEY,
    name varchar(50) NOT NULL,
    price int NOT NULL,
    dateTimeAdded DATE
);