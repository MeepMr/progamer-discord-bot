-- liquibase formatted sql

-- changeset jklein:v0-1
CREATE TABLE "test_1"
(
    "id" BIGINT NOT NULL,
    CONSTRAINT "test_1_pkey" PRIMARY KEY ("id")
);
-- rollback: DROP TABLE "test";

-- changeset jklein:v0-2
CREATE TABLE "test_2"
(
    "id" BIGINT NOT NULL,
    CONSTRAINT "test_2_pkey" PRIMARY KEY ("id")
);
-- rollback: DROP TABLE "test_2";

-- changeset name:version-changesetNumber

-- comment: some comment
