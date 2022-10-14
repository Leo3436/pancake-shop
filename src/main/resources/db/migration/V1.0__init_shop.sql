CREATE TABLE ingredients (
    id SERIAL NOT NULL PRIMARY KEY,
    ing_name VARCHAR(50) NOT NULL UNIQUE,
    price INTEGER NOT NULL,
    is_healthy BOOL NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    id SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(200),
    placement_time TIME,
    total_price INTEGER
);

CREATE TABLE IF NOT EXISTS pancakes(
    id SERIAL NOT NULL PRIMARY KEY,
    price INTEGER,
    order_id SERIAL
);