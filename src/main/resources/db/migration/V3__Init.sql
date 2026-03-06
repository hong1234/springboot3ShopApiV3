-- DROP TABLE customers IF EXISTS;
-- DROP TABLE carts IF EXISTS;
-- DROP TABLE cartitems IF EXISTS;
-- DROP TABLE orders IF EXISTS;
-- DROP TABLE orderitems IF EXISTS; 

CREATE TABLE customers (
    id uuid NOT NULL DEFAULT random_uuid(),
    email VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    -- role VARCHAR(100) NOT NULL,
    authority VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE carts (
    id uuid NOT NULL DEFAULT random_uuid(),
    created_at timestamp NULL DEFAULT NULL,
    user_id uuid NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES customers(id)
    -- CONSTRAINT fk_customer FOREIGN KEY (user_id) REFERENCES customers(id)
);

CREATE TABLE cartitems (
    id uuid NOT NULL DEFAULT random_uuid(),
    qty INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    -- created_at timestamp NULL DEFAULT NULL,
    product_id uuid NOT NULL,
    cart_id uuid NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (cart_id) REFERENCES carts(id)
    -- CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id),
    -- CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts(id)
);

CREATE TABLE orders (
    id uuid NOT NULL DEFAULT random_uuid(),
    created_at timestamp NULL DEFAULT NULL,
    items_price DECIMAL(10, 2) NOT NULL,
    shipment_price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    user_id uuid NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES customers(id)
    -- CONSTRAINT fk_customer_order FOREIGN KEY (user_id) REFERENCES customers(id)
);

CREATE TABLE orderitems (
    id uuid NOT NULL DEFAULT random_uuid(),
    qty INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    title VARCHAR(100) NOT NULL,
    product_id uuid NOT NULL,
    order_id uuid NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
    -- CONSTRAINT fk_product_order FOREIGN KEY (product_id) REFERENCES products(id),
    -- CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id) 
);








