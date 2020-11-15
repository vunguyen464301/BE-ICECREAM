# BE-ICECREAM

run mysql

Step 1 : 
==> run MySql workpench
CREATE DATABASE IF NOT EXISTS `icecream5`;
USE `icecream5`;


Step 2 : 
==================>after run IDE Eclipse to run BackEnd

Step 3 :
==================>after run code mysql insert database

INSERT INTO `role` values(null,"ROLE_ADMIN");
INSERT INTO `role` values(null,"ROLE_USER");
INSERT INTO `role` values(null,"ROLE_CUSTOMER");
INSERT INTO `role` values(null,"ROLE_GUEST");

INSERT INTO `status` values(null,"Actived");
INSERT INTO `status` values(null,"No Actived");
INSERT INTO `status` values(null,"Remove");
INSERT INTO `status` values(null,"Hide");

INSERT INTO `catalogue` values(null,"Kem trẻ em");
INSERT INTO `catalogue` values(null,"Kem trộn");
INSERT INTO `catalogue` values(null,"Kem đá");
INSERT INTO `catalogue` values(null,"Kem mềm");

INSERT INTO `account` (`username`,`password`,`status_id`)
values("admin","$2a$10$T8d/kHm.QSZX.RTTr4vZ5uUpiePbWmbgxFJmeCZICk2z6RuS/6UiG",1);
INSERT INTO `account` (`username`,`password`,`status_id`)
values("user","$2a$10$T8d/kHm.QSZX.RTTr4vZ5uUpiePbWmbgxFJmeCZICk2z6RuS/6UiG",1);
INSERT INTO `account` (`username`,`password`,`status_id`)
values("customer","$2a$10$T8d/kHm.QSZX.RTTr4vZ5uUpiePbWmbgxFJmeCZICk2z6RuS/6UiG",1);

INSERT  into `account_role` values(1,1);
INSERT  into `account_role` values(2,2);
INSERT  into `account_role` values(3,3);

Step 4 :
==============>run Frontend with IDE any to run
npm start

Step 5 :

login Frontend

====>admin
username: admin
password: 123456

====>user
username: user
password: 123456

====>customer
username: customer
password: 123456
