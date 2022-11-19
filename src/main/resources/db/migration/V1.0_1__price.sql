CREATE TABLE price (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    product_id BIGINT NOT NULL,
    brand_id INT NOT NULL,
    start_date BIGINT NOT NULL,
    end_date BIGINT NOT NULL,
    price_rate_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(5) NOT NULL,
    priority INT NOT NULL
);