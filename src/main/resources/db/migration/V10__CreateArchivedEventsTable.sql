CREATE TABLE archived_events (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 organizer_id BIGINT NOT NULL,
                                 date TIMESTAMP NOT NULL,
                                 winner_id BIGINT NOT NULL,
                                 loser_id BIGINT NOT NULL,
                                 place VARCHAR(255) NOT NULL,
                                 FOREIGN KEY (organizer_id) REFERENCES app_users(id),
                                 FOREIGN KEY (winner_id) REFERENCES boxers(id),
                                 FOREIGN KEY (loser_id) REFERENCES boxers(id)
);