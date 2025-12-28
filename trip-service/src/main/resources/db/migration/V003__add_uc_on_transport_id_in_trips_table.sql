-- Backfill existing NULL transport_id values with a default value
UPDATE trips
SET transport_id = '00000000-0000-0000-0000-000000000000'
WHERE transport_id IS NULL;

-- Alter the transport_id column to set NOT NULL constraint
ALTER TABLE trips
    ALTER COLUMN transport_id SET NOT NULL;