ALTER TABLE travelers
    ADD email VARCHAR(255);

ALTER TABLE travelers
    ADD first_name VARCHAR(50);

ALTER TABLE travelers
    ADD last_name VARCHAR(50);

ALTER TABLE travelers
    ADD phone_number VARCHAR(255);

-- Backfill existing records with default values

UPDATE travelers
SET email = CONCAT('user_', id, '@example.com')
WHERE email IS NULL;

UPDATE travelers
SET first_name = 'Unknown'
WHERE first_name IS NULL;

UPDATE travelers
SET last_name = 'Unknown'
WHERE last_name IS NULL;

UPDATE travelers
SET phone_number = '0000000000'
WHERE phone_number IS NULL;

-- Apply NOT NULL constraints and unique constraint

ALTER TABLE travelers
    ALTER COLUMN email SET NOT NULL;

ALTER TABLE travelers
    ALTER COLUMN first_name SET NOT NULL;

ALTER TABLE travelers
    ALTER COLUMN last_name SET NOT NULL;

ALTER TABLE travelers
    ALTER COLUMN phone_number SET NOT NULL;

ALTER TABLE travelers
    ADD CONSTRAINT uc_travelers_email UNIQUE (email);