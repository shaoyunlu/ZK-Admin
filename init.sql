DROP TABLE IF EXISTS r_user_role;
DROP TABLE IF EXISTS r_role_permission;
DROP TABLE IF EXISTS c_user;
DROP TABLE IF EXISTS c_role;
DROP TABLE IF EXISTS c_permission;
DROP TABLE IF EXISTS c_menu;

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

CREATE TABLE c_menu (
  menu_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  menu_name VARCHAR(255) NOT NULL COMMENT '菜单名称',
  menu_parent_id INT UNSIGNED DEFAULT 0 COMMENT '父级菜单ID，0表示顶级菜单',
  menu_url VARCHAR(255) DEFAULT '' COMMENT '菜单链接地址',
  menu_order INT DEFAULT 0 COMMENT '菜单排序',
  menu_status TINYINT(1) DEFAULT 1 COMMENT '状态（1：启用，0：禁用）',
  menu_icon VARCHAR(255) DEFAULT '' COMMENT '菜单图标'
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

INSERT INTO `c_user` (`user_name`, `user_password`) VALUES ('admin', '123456');
INSERT INTO `c_role` (`role_name`, `role_desc`) VALUES ('管理员', '拥有系统的全部权限。');
INSERT INTO `c_role` (`role_name`, `role_desc`) VALUES ('普通用户', '系统中的普通用户。');
INSERT INTO `r_user_role` (`ur_user_id`,`ur_role_id`) VALUES (1 ,1);
INSERT INTO `c_menu` (`menu_name`, `menu_parent_id`, `menu_url`, `menu_order`, `menu_status`, `menu_icon`) VALUES ('基础配置', 0, '', 1, 1, 'base');
INSERT INTO `c_menu` (`menu_name`, `menu_parent_id`, `menu_url`, `menu_order`, `menu_status`, `menu_icon`) VALUES ('用户管理', 1, '/frame/user', 1, 1, '');
INSERT INTO `c_menu` (`menu_name`, `menu_parent_id`, `menu_url`, `menu_order`, `menu_status`, `menu_icon`) VALUES ('角色管理', 1, '/frame/role', 2, 1, 'icon-role');
INSERT INTO `c_menu` (`menu_name`, `menu_parent_id`, `menu_url`, `menu_order`, `menu_status`, `menu_icon`) VALUES ('权限管理', 1, '/frame/permission', 3, 1, 'icon-permission');
