create database organization_db;
CREATE TABLE organizations (
                               id VARCHAR(36) NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               description VARCHAR(255),
                               code VARCHAR(255) UNIQUE NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               PRIMARY KEY (id)
);
-- UUID_TO_BIN - new function
CREATE FUNCTION UUID_TO_BIN(uuid_str CHAR(36))
    RETURNS BINARY(16)
    DETERMINISTIC
    RETURN UNHEX(REPLACE(uuid_str, '-', ''));

-- UUID_FROM_BIN - new function
CREATE FUNCTION UUID_FROM_BIN(uuid_bin BINARY(16)) RETURNS CHAR(36)
    DETERMINISTIC
BEGIN
    DECLARE
        hex_str CHAR(32);
    SET
        hex_str = HEX(uuid_bin);
    RETURN CONCAT(LEFT(hex_str, 8), '-', MID(hex_str, 9, 4), '-', MID(hex_str, 13, 4), '-',
                  MID(hex_str, 17, 4), '-', RIGHT(hex_str, 12));
END;