BEGIN;

INSERT INTO app_users (username, email, password)
VALUES ('admin', 'admin@example.com', '$2a$12$N5QYRKpRJvxyqhTMjceK6uCQz7QuHiXRJNE.RELbvYIIJUHimI8pq');

SET @userId = (SELECT id FROM app_users WHERE username = 'admin');

INSERT INTO user_type (type_name, user_id)
VALUES ('ADMINISTRATOR', @userId);

COMMIT;
