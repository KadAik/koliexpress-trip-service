ALTER TABLE travelers
    ADD email VARCHAR(255);

ALTER TABLE travelers
    ADD first_name VARCHAR(50);

ALTER TABLE travelers
    ADD last_name VARCHAR(50);

ALTER TABLE travelers
    ADD phone_number VARCHAR(255);

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

ALTER TABLE trips
    ALTER COLUMN transport_id SET NOT NULL;