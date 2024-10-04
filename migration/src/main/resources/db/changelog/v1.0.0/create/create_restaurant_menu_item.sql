
create table if not exists restaurant_menu_item
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    restaurant_id int REFERENCES restaurant(id) ON DELETE CASCADE NOT NULL,
    name varchar(100) NOT NULL,
    price int NOT NULL,
    image varchar(255),
    description varchar(255)
);