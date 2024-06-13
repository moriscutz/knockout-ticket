CREATE TABLE event_fight_nights (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    date DATE NOT NULL
);

ALTER TABLE events ADD COLUMN event_fight_night_id BIGINT;

ALTER TABLE events
    ADD CONSTRAINT fk_event_fight_night
        FOREIGN KEY (event_fight_night_id) REFERENCES event_fight_nights(id);
