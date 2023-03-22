-- liquibase formatted sql

-- changeset justinklein:1679088601592-1
ALTER TABLE birthday_config
    ADD birthday_message VARCHAR(255);

-- changeset justinklein:1679089290268-1
UPDATE birthday_config
SET birthday_message = 'Happy Birthday, ${USER_NAME}'
WHERE birthday_message IS NULL;
ALTER TABLE birthday_config
    ALTER COLUMN birthday_message SET NOT NULL;

-- changeset justinklein:1679232734026-1
ALTER TABLE birthday_config
    ALTER COLUMN birthday_message SET DEFAULT 'Happy Birthday, ${USER_NAME}';

-- changeset justinklein:1679513465571-1
CREATE SEQUENCE IF NOT EXISTS birthday_config_seq START WITH 1 INCREMENT BY 50;
