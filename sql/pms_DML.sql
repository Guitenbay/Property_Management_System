INSERT INTO `community` VALUES (1, 'A', 200000.00, 200.00, 1000.00, 1.00);
INSERT INTO `community` VALUES (2, 'B', 220000.00, 250.00, 1500.00, 1.50);
INSERT INTO `community` VALUES (3, 'C', 240000.00, 300.00, 2000.00, 2.00);

INSERT INTO `equipment_info` VALUES ('INDOOR', '楼层照明灯', 1, 10.00);
INSERT INTO `equipment_info` VALUES ('INDOOR', '楼房门禁设备', 1, 120.00);
INSERT INTO `equipment_info` VALUES ('INDOOR', '楼道声控灯', 1, 30.00);
INSERT INTO `equipment_info` VALUES ('INDOOR', '水闸', 1, 10.00);
INSERT INTO `equipment_info` VALUES ('INDOOR', '电梯', 1, 300.00);
INSERT INTO `equipment_info` VALUES ('INDOOR', '电闸', 1, 20.00);
INSERT INTO `equipment_info` VALUES ('OUTDOOR', '健身设备', 6, 100.00);
INSERT INTO `equipment_info` VALUES ('OUTDOOR', '公告牌', 6, 15.00);
INSERT INTO `equipment_info` VALUES ('OUTDOOR', '小区内照明灯', 6, 10.00);
INSERT INTO `equipment_info` VALUES ('OUTDOOR', '躺椅', 6, 40.00);

INSERT INTO `equipment` VALUES (1, 'INDOOR', '楼层照明灯', '高科苑四号楼1楼');
INSERT INTO `equipment` VALUES (2, 'INDOOR', '楼层照明灯', '高科苑四号楼6楼');
INSERT INTO `equipment` VALUES (3, 'INDOOR', '楼房门禁设备', '高科苑四号楼');
INSERT INTO `equipment` VALUES (4, 'INDOOR', '楼道声控灯', '高科苑四号楼1楼左侧楼道');
INSERT INTO `equipment` VALUES (5, 'INDOOR', '楼道声控灯', '高科苑四号楼1楼右侧楼道');
INSERT INTO `equipment` VALUES (6, 'INDOOR', '水闸', '高科苑四号楼6楼');
INSERT INTO `equipment` VALUES (7, 'INDOOR', '电梯', '高科苑四号楼1号电梯');
INSERT INTO `equipment` VALUES (8, 'INDOOR', '电梯', '高科苑四号楼2号电梯');
INSERT INTO `equipment` VALUES (9, 'INDOOR', '电闸', '高科苑四号楼6楼');

INSERT INTO `equipment_record` VALUES (1, 1);
INSERT INTO `equipment_record` VALUES (1, 2);
INSERT INTO `equipment_record` VALUES (1, 3);
INSERT INTO `equipment_record` VALUES (1, 4);
INSERT INTO `equipment_record` VALUES (1, 5);
INSERT INTO `equipment_record` VALUES (1, 6);
INSERT INTO `equipment_record` VALUES (1, 7);
INSERT INTO `equipment_record` VALUES (1, 8);
INSERT INTO `equipment_record` VALUES (1, 9);

INSERT INTO `resident` VALUES ('520131198606075321', '王五', '15053684569');
INSERT INTO `resident` VALUES ('521321197502143512', '李四', '18713695204');
INSERT INTO `resident` VALUES ('522420199710112356', '马兵', '155536945687');
INSERT INTO `resident` VALUES ('522422199810113213', '张健', '17521680487');


INSERT INTO `residence` VALUES (1, 6, 4, 1, 60.00);
INSERT INTO `residence` VALUES (2, 6, 4, 10, 50.00);
INSERT INTO `residence` VALUES (3, 7, 4, 1, 60.00);
INSERT INTO `residence` VALUES (4, 7, 4, 2, 50.00);
INSERT INTO `residence` VALUES (5, 7, 4, 3, 50.00);
INSERT INTO `residence` VALUES (6, 6, 4, 2, 50.00);
INSERT INTO `residence` VALUES (7, 6, 4, 3, 50.00);
INSERT INTO `residence` VALUES (8, 6, 4, 4, 50.00);
INSERT INTO `residence` VALUES (9, 6, 4, 5, 50.00);
INSERT INTO `residence` VALUES (10, 6, 4, 6, 50.00);
INSERT INTO `residence` VALUES (11, 6, 4, 7, 50.00);
INSERT INTO `residence` VALUES (12, 6, 4, 8, 50.00);
INSERT INTO `residence` VALUES (13, 6, 4, 9, 50.00);


INSERT INTO `property_record` VALUES ('520131198606075321', 7, '2019-01-04 13:24:33', 50000.00);
INSERT INTO `property_record` VALUES ('521321197502143512', 1, '2019-01-04 12:04:38', 60000.00);
INSERT INTO `property_record` VALUES ('522420199710112356', 8, '2019-01-04 14:15:16', 50000.00);
INSERT INTO `property_record` VALUES ('522422199810113213', 9, '2019-01-04 14:18:44', 50000.00);

INSERT INTO `residence_record` VALUES (1, 1, 'BUSY');
INSERT INTO `residence_record` VALUES (1, 2, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 3, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 4, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 5, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 6, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 7, 'BUSY');
INSERT INTO `residence_record` VALUES (1, 8, 'BUSY');
INSERT INTO `residence_record` VALUES (1, 9, 'BUSY');
INSERT INTO `residence_record` VALUES (1, 10, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 11, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 12, 'IDLE');
INSERT INTO `residence_record` VALUES (1, 13, 'IDLE');

INSERT INTO `vehicle` VALUES ('A1234', '东方');

INSERT INTO `payment_record` VALUES (1, 200000.00, 'PERMANENT', 0, '2019-01-04 17:52:13', 'YES');
INSERT INTO `payment_record` VALUES (4, 800.00, 'MONTH', 4, '2019-01-05 12:13:49', 'NO');
INSERT INTO `payment_record` VALUES (6, 5.00, 'HOUR', 5, '2019-01-04 23:34:16', 'NO');
INSERT INTO `payment_record` VALUES (7, 50.00, 'MONTH', 1, '2019-01-05 12:14:28', 'YES');
INSERT INTO `payment_record` VALUES (8, 50.00, 'MONTH', 1, '2019-01-05 12:16:17', 'YES');


INSERT INTO `purchased_pks` VALUES (1, '522422199810113213', 50.00);

INSERT INTO `rented_pks` VALUES (11, '521321197502143512', 1.00, 800.00, 50.00);

INSERT INTO `temporary_pks` VALUES (21, 'A1234');


INSERT INTO `pks_fee_record` VALUES (1, 1, '购买车位');
INSERT INTO `pks_fee_record` VALUES (4, 11, '租用车位');
INSERT INTO `pks_fee_record` VALUES (6, 21, '临时车位');

INSERT INTO `pks_management_fee_record` VALUES (7, 11);

INSERT INTO `property_fee_record` VALUES (8, '522422199810113213', 9);

INSERT INTO `parking_space` VALUES (1, 1, 'BUSY', 'PURCHASED');
INSERT INTO `parking_space` VALUES (2, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (3, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (4, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (5, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (6, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (7, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (8, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (9, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (10, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (11, 1, 'BUSY', 'RENTED');
INSERT INTO `parking_space` VALUES (12, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (13, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (14, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (15, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (16, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (17, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (18, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (19, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (20, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (21, 1, 'BUSY', 'TEMPORARY');
INSERT INTO `parking_space` VALUES (22, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (23, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (24, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (25, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (26, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (27, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (28, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (29, 1, 'IDLE', '');
INSERT INTO `parking_space` VALUES (30, 1, 'IDLE', '');

INSERT INTO `repair_order` VALUES (1, '522422199810113213', 9, '2019-01-05 12:35:35', '电闸接触不良', 'TODO', NULL);
