INSERT INTO `sonarexample`.`configurations` (`id`,`clave`, `value`) VALUES (1, 'intentos_permitidos_login', '3');
insert into sonarexample.users(username, enabled, password, login_try) VALUE ('erodriguez', 1, 'password', 0);
INSERT INTO sonarexample.user_role(user_role_id, role, username) VALUE (1, 'ADMIN', 'erodriguez');
