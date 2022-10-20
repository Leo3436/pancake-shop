CREATE TABLE IF NOT EXISTS ingredients(
    id SERIAL NOT NULL PRIMARY KEY,
    ing_name VARCHAR(50) NOT NULL UNIQUE,
    price INTEGER NOT NULL,
    healthy BOOL NOT NULL,
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
    order_id INTEGER references orders(id)
);

CREATE TABLE IF NOT EXISTS madeof(
    pancake_id SERIAL NOT NULL references pancakes(id),
    id SERIAL NOT NULL references ingredients(id),
    primary key (pancake_id, id)
);

