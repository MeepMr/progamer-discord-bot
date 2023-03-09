--liquibase formatted sql

-- changeset jklein:v0-1
CREATE TABLE discord_member
(
    id uuid NOT NULL,
    discord_id BIGINT,
    guild_id BIGINT,
    nickname VARCHAR(255),
    birth_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_discordmember PRIMARY KEY (id)
);

-- changeset jklein:v0-2
CREATE TABLE birthday_config
(
    id BIGINT NOT NULL,
    birthday_channel_id BIGINT,
    enabled BOOLEAN NOT NULL,
    CONSTRAINT pk_birthdayconfig PRIMARY KEY (id)
);

-- changeset jklein:v0-3
CREATE TABLE discord_guild
(
    guild_id BIGINT NOT NULL,
    guild_name VARCHAR(255),
    birthday_config_id BIGINT,
    CONSTRAINT pk_discordguild PRIMARY KEY (guild_id)
);

ALTER TABLE discord_guild
    ADD CONSTRAINT fk_discordguild_on_birthdayconfig FOREIGN KEY (birthday_config_id) REFERENCES birthday_config (id);
