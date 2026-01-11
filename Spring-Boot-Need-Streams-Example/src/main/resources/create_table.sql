
CREATE TABLE test_schema_monolith.stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    symbol VARCHAR(20) NOT NULL UNIQUE,
    value VARCHAR(20) NOT NULL,
    sector VARCHAR(100) NOT NULL,
    industry VARCHAR(100) NOT NULL

);