ALTER TABLE bookings ADD COLUMN customer_id BIGINT NOT NULL;


ALTER TABLE bookings
    DROP CONSTRAINT FKqferkk98p42soamfn02tqt6sm;

ALTER TABLE bookings DROP COLUMN user_id;


ALTER TABLE bookings
    ADD CONSTRAINT fk_customer
        FOREIGN KEY (customer_id) REFERENCES app_users(id);
