CREATE TABLE bookings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          event_fight_night_id BIGINT NOT NULL,
                          FOREIGN KEY (event_fight_night_id) REFERENCES event_fight_nights(id)
);