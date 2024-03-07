DROP TABLE IF EXISTS R_UserRole;
DROP TABLE IF EXISTS R_RolePermission;
DROP TABLE IF EXISTS C_User;
DROP TABLE IF EXISTS C_Role;
DROP TABLE IF EXISTS C_Permission;

CREATE TABLE C_User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255),
    user_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_name),
    UNIQUE (user_email)
);


CREATE TABLE C_Role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL,
    role_desc TEXT
);


CREATE TABLE C_Permission (
    permission_id INT PRIMARY KEY AUTO_INCREMENT,
    permission_name VARCHAR(255) NOT NULL,
    Description TEXT
);

CREATE TABLE R_UserRole (
    ur_user_id INT,
    ur_role_id INT,
    PRIMARY KEY (ur_user_id, ur_role_id),
    FOREIGN KEY (ur_user_id) REFERENCES C_User(user_id),
    FOREIGN KEY (ur_role_id) REFERENCES C_Role(role_id)
);

CREATE TABLE R_RolePermission (
    rp_role_id INT,
    rp_permission_id INT,
    PRIMARY KEY (rp_role_id, rp_permission_id),
    FOREIGN KEY (rp_role_id) REFERENCES C_Role(role_id),
    FOREIGN KEY (rp_permission_id) REFERENCES C_Permission(permission_id)
);

INSERT INTO C_User (user_name, user_password) VALUES ('admin', '123456');