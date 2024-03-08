DROP TABLE IF EXISTS r_user_role;
DROP TABLE IF EXISTS r_role_permission;
DROP TABLE IF EXISTS c_user;
DROP TABLE IF EXISTS c_role;
DROP TABLE IF EXISTS c_permission;

CREATE TABLE c_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255),
    user_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_name),
    UNIQUE (user_email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE c_role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    role_desc TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE c_permission (
    permission_id INT PRIMARY KEY AUTO_INCREMENT,
    permission_name VARCHAR(255) NOT NULL,
    Description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE r_user_role (
    ur_user_id INT,
    ur_role_id INT,
    PRIMARY KEY (ur_user_id, ur_role_id),
    FOREIGN KEY (ur_user_id) REFERENCES c_user(user_id),
    FOREIGN KEY (ur_role_id) REFERENCES c_role(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE r_role_permission (
    rp_role_id INT,
    rp_permission_id INT,
    PRIMARY KEY (rp_role_id, rp_permission_id),
    FOREIGN KEY (rp_role_id) REFERENCES c_role(role_id),
    FOREIGN KEY (rp_permission_id) REFERENCES c_permission(permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO c_user (user_name, user_password) VALUES ('admin', '123456');