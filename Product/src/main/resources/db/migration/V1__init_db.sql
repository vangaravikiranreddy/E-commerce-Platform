CREATE TABLE category
(
    id           BINARY(16) NOT NULL,
    created_date VARCHAR(255) NULL,
    name         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           BINARY(16) NOT NULL,
    created_date VARCHAR(255) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE orders_products
(
    orders_id   BINARY(16) NOT NULL,
    products_id BINARY(16) NOT NULL
);

CREATE TABLE price
(
    id           BINARY(16) NOT NULL,
    created_date VARCHAR(255) NULL,
    currency     VARCHAR(255) NULL,
    value        DOUBLE NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BINARY(16) NOT NULL,
    created_date  VARCHAR(255) NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    category_id   BINARY(16) NOT NULL,
    price_id      BINARY(16) NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE orders_products
    ADD CONSTRAINT fk_ordpro_on_order FOREIGN KEY (orders_id) REFERENCES orders (id);

ALTER TABLE orders_products
    ADD CONSTRAINT fk_ordpro_on_product FOREIGN KEY (products_id) REFERENCES product (id);