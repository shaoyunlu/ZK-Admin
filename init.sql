DROP TABLE IF EXISTS User;

CREATE TABLE User (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_name),
    UNIQUE (user_email)
);

DROP TABLE IF EXISTS Role;
CREATE TABLE Role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    role_desc TEXT
);