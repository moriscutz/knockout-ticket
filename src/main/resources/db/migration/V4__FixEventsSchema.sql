-- Start transaction
START TRANSACTION;

-- Drop foreign key constraints associated with the columns
ALTER TABLE events DROP FOREIGN KEY events_ibfk_1;
ALTER TABLE events DROP FOREIGN KEY events_ibfk_2;

-- Drop the columns after removing foreign key constraints
ALTER TABLE events DROP COLUMN boxer1_id;
ALTER TABLE events DROP COLUMN boxer2_id;

-- Commit changes
COMMIT;