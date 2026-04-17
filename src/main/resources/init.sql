CREATE SCHEMA IF NOT EXISTS postgres;
SET search_path TO postgres;
CREATE TABLE IF NOT EXISTS postgres.seller_account (
                                                                      id      BIGSERIAL PRIMARY KEY,      -- Maps to Long in Java
                                                                      name    VARCHAR(255) NOT NULL,
    owner   VARCHAR(255),
    is_test BOOLEAN NOT NULL DEFAULT FALSE,
    type    VARCHAR(255),
    status  INTEGER NOT NULL            -- Maps to Integer/int in Java
    );
INSERT INTO postgres.seller_account (name, owner, is_test, type, status)
VALUES
    ('harry', 'manomano', true, 'STD', 1),
    ('mike', 'amazon', true, 'STD', 1),
    ('David', 'maomano', true, 'STD', 1),
    ('davids', 'manomano', true, 'STD', 1),
    ('lucas', 'flipkart', true, 'STD', 1);