ALTER TABLE bookings ADD COLUMN customer_id BIGINT NOT NULL;


ALTER TABLE bookings
    ADD CONSTRAINT fk_customer
        FOREIGN KEY (customer_id) REFERENCES app_users(id);
