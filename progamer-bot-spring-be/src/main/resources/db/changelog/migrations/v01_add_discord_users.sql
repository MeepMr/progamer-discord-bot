--liquibase formatted sql

-- changeset jklein:v0-1
CREATE TABLE discord_member
(
    id uuid NOT NULL,
    discord_id bigint,
    guild_id bigint,
    nickname VARCHAR(255),
    birth_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_discordmember PRIMARY KEY (id)
);
