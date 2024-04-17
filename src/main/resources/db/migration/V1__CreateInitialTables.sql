CREATE TABLE app_users (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        organization_name VARCHAR(255)
);

CREATE TABLE user_type (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        type_name ENUM('NORMAL_USER', 'BOXER', 'EVENT_ORGANIZER', 'ADMINISTRATOR') NOT NULL,
                        user_id BIGINT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES app_users(id)
);

CREATE TABLE boxers (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        full_name VARCHAR(255),
                        weight_class ENUM('FEATHERWEIGHT', 'LIGHTWEIGHT', 'WELTERWEIGHT', 'MIDDLEWEIGHT', 'LIGHT_HEAVYWEIGHT', 'HEAVYWEIGHT'),
                        wins INT,
                        losses INT,
                        draws INT,
                        weight FLOAT,
                        age INT,
                        app_user_id BIGINT NOT NULL,
                        FOREIGN KEY (app_user_id) REFERENCES app_users(id)
);

CREATE TABLE events (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        boxer1_id BIGINT,
                        boxer2_id BIGINT,
                        organizer_id BIGINT,
                        event_date DATETIME,
                        event_status ENUM('SCHEDULED', 'ONGOING', 'COMPLETED', 'DRAW', 'NO_CONTEST'),
                        winner_id BIGINT,
                        place VARCHAR(255),
                        FOREIGN KEY (boxer1_id) REFERENCES boxers(id),
                        FOREIGN KEY (boxer2_id) REFERENCES boxers(id),
                        FOREIGN KEY (organizer_id) REFERENCES app_users(id),
                        FOREIGN KEY (winner_id) REFERENCES boxers(id)
);