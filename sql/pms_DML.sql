INSERT INTO community(community_name, pks_purchase_fee,pks_rental_fee,residence_price) VALUES('A', 200000, 200, 1);
INSERT INTO community(community_name, pks_purchase_fee,pks_rental_fee,residence_price) VALUES('B', 220000, 250, 1.5);
INSERT INTO community(community_name, pks_purchase_fee,pks_rental_fee,residence_price) VALUES('C', 240000, 300, 2);

INSERT INTO equipment_info VALUES('OUTDOOR', '健身设备', 6, 100);
INSERT INTO equipment_info VALUES('OUTDOOR', '小区内照明灯', 6, 10);
INSERT INTO equipment_info VALUES('OUTDOOR', '公告牌', 6, 15);
INSERT INTO equipment_info VALUES('OUTDOOR', '躺椅', 6, 40);
INSERT INTO equipment_info VALUES('INDOOR', '楼道声控灯', 1, 30);
INSERT INTO equipment_info VALUES('INDOOR', '楼房门禁设备', 1, 120);
INSERT INTO equipment_info VALUES('INDOOR', '电梯', 1, 300);
INSERT INTO equipment_info VALUES('INDOOR', '楼层照明灯', 1, 10);
INSERT INTO equipment_info VALUES('INDOOR', '水闸', 1, 10);
INSERT INTO equipment_info VALUES('INDOOR', '电闸', 1, 20);

INSERT INTO `resident`(resident_id, resident_name, phonenumber) VALUES ('522422199810113213', '张健', '18717723912');

INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 1, 60.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 10, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (7, 4, 1, 60.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (7, 4, 2, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (7, 4, 3, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 2, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 3, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 4, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 5, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 6, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 7, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 8, 50.00);
INSERT INTO `residence`(floor_num, unit_num, room_num, area) VALUES (6, 4, 9, 50.00);


INSERT INTO property_record VALUES ('522422199810113213', 9, '2019-01-03 15:18:36', 2000000.00);

INSERT INTO `residence_record` VALUES (1, 1, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 10, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 11, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 12, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 13, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 2, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 3, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 4, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 5, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 6, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 7, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 8, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 9, 'BUSY');


INSERT INTO parking_space VALUES(1, 1, 'BUSY', 'PURCHASED');
INSERT INTO parking_space VALUES(2, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(3, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(4, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(5, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(6, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(7, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(8, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(9, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(10, 1, 'IDLE', 'PURCHASED');
INSERT INTO parking_space VALUES(11, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(12, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(13, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(14, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(15, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(16, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(17, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(18, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(19, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(20, 1, 'IDLE', 'RENTED');
INSERT INTO parking_space VALUES(21, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(22, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(23, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(24, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(25, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(26, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(27, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(28, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(29, 1, 'IDLE', 'TEMPORARY');
INSERT INTO parking_space VALUES(30, 1, 'IDLE', 'TEMPORARY');

INSERT INTO purchased_pks VALUES (1, '522422199810113213', 50.00);


