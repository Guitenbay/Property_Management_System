SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS complaint_order; 
DROP TABLE IF EXISTS maintenance_record;
DROP TABLE IF EXISTS inspect_record;
DROP TABLE IF EXISTS repair_order;
DROP TABLE IF EXISTS equipment_record;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS equipment_info;
DROP TABLE IF EXISTS pks_management_fee_record;
DROP TABLE IF EXISTS pks_fee_record;
DROP TABLE IF EXISTS property_fee_record;
DROP TABLE IF EXISTS payment_record;
DROP TABLE IF EXISTS purchased_pks;
DROP TABLE IF EXISTS rented_pks;
DROP TABLE IF EXISTS temporary_pks;
DROP TABLE IF EXISTS parking_space;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS property_record;
DROP TABLE IF EXISTS resident;
DROP TABLE IF EXISTS residence_record;
DROP TABLE IF EXISTS residence;
DROP TABLE IF EXISTS community;

CREATE TABLE community (
	community_id      INT NOT NULL AUTO_INCREMENT,
	community_name		varchar(255) NOT NULL,
	pks_purchase_fee	numeric(20, 2) CHECK (pks_purchase_fee >= 0),
	pks_rental_fee		numeric(15, 2) CHECK (pks_rental_fee >= 0),
	residence_price		numeric(10, 2) CHECK (residence_price >= 0),
	PRIMARY KEY (community_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic; 

CREATE TABLE residence (
	residence_id	INT NOT NULL AUTO_INCREMENT,
	floor_num	numeric(5, 0) NOT NULL,
	unit_num	numeric(6, 0) CHECK (unit_num > 0),
	room_num	numeric(8, 0) CHECK (room_num > 0),
	area		numeric(10, 2) CHECK (area > 0),
	PRIMARY KEY (residence_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE residence_record (
	community_id	INT NOT NULL,
	residence_id	INT NOT NULL,
	residence_state	varchar(5) CHECK (residence_state IN ('IDLE', 'BUSY')),
	PRIMARY KEY (community_id, residence_id),
	FOREIGN KEY (community_id) REFERENCES community(community_id) ON DELETE CASCADE,
	FOREIGN KEY (residence_id) REFERENCES residence(residence_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE resident (
	resident_id	  INT NOT NULL AUTO_INCREMENT,
	resident_name	varchar(255) NOT NULL,
	phonenumber	varchar(255),
	PRIMARY KEY (resident_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE property_record (
	resident_id	  INT NOT NULL,
	residence_id	INT NOT NULL,
	issue_date	timestamp NOT NULL,
	purchased_fee	numeric(20, 2) CHECK (purchased_fee >= 0),
	PRIMARY KEY (resident_id, residence_id),
	FOREIGN KEY (resident_id) REFERENCES resident(resident_id),
	FOREIGN KEY (residence_id) REFERENCES residence(residence_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE vehicle (
	license_plate	varchar(20) NOT NULL,
	owner_name	varchar(15) NOT NULL,
	PRIMARY KEY (license_plate)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE parking_space (
	pks_id		INT NOT NULL AUTO_INCREMENT,
	community_id  INT NOT NULL,
	pks_state	varchar(10) DEFAULT 'IDLE' CHECK (pks_state IN ('IDLE', 'BUSY')),
	pks_type	varchar(15) CHECK (pks_type IN ('TEMPORARY', 'RENTED', 'PURCHASED')),
	PRIMARY KEY (pks_id),
	FOREIGN KEY (community_id) REFERENCES community(community_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE temporary_pks (
	pks_id		INT NOT NULL,
	license_plate varchar(20),
	PRIMARY KEY (pks_id),
	FOREIGN KEY (pks_id) REFERENCES parking_space(pks_id) ON DELETE CASCADE,
	FOREIGN KEY (license_plate) REFERENCES vehicle(license_plate) ON DELETE SET NULL
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE rented_pks (
	pks_id		INT NOT NULL,
	resident_id	INT,
	discount  numeric(2, 2) CHECK (discount >= 0 AND discount <= 1),
	discount_price numeric(20, 2) CHECK (discount_price >= 0),
	man_fee		numeric(8, 2) DEFAULT 0 CHECK (man_fee >= 0),
	PRIMARY KEY (pks_id),
	FOREIGN KEY (pks_id) REFERENCES parking_space(pks_id) ON DELETE CASCADE,
	FOREIGN KEY (resident_id) REFERENCES resident(resident_id) ON DELETE SET NULL
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE purchased_pks (
	pks_id		  INT NOT NULL,
	resident_id	INT,
	man_fee		numeric(8, 2) DEFAULT 0 CHECK (man_fee >= 0),
	PRIMARY KEY (pks_id),
	FOREIGN KEY (pks_id) REFERENCES parking_space(pks_id) ON DELETE CASCADE,
	FOREIGN KEY (resident_id) REFERENCES resident(resident_id) ON DELETE SET NULL
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE payment_record (
	payment_id	INT NOT NULL AUTO_INCREMENT,
	payment		numeric(20, 2) CHECK (payment >= 0),
	duration	numeric(5, 1) CHECK (duration > 0),
	start_time	timestamp NOT NULL,
	checked		varchar(5) CHECK (checked in ('YES', 'NO')),
	PRIMARY KEY (payment_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE property_fee_record (
	payment_id	INT NOT NULL,
	resident_id	INT NOT NULL,
	PRIMARY KEY (payment_id, resident_id),
	FOREIGN KEY (payment_id) REFERENCES payment_record(payment_id) ON DELETE CASCADE,
	FOREIGN KEY (resident_id) REFERENCES resident(resident_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE pks_fee_record (
	payment_id	INT NOT NULL,
	pks_id		INT NOT NULL,
	description	varchar(1024) NOT NULL,
	PRIMARY KEY (payment_id, pks_id),
	FOREIGN KEY (payment_id) REFERENCES payment_record(payment_id) ON DELETE CASCADE,
	FOREIGN KEY (pks_id) REFERENCES parking_space(pks_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE pks_management_fee_record (
	payment_id	INT NOT NULL,
	pks_id		INT NOT NULL,
	PRIMARY KEY (payment_id, pks_id),
	FOREIGN KEY (payment_id) REFERENCES payment_record(payment_id) ON DELETE CASCADE,
	FOREIGN KEY (pks_id) REFERENCES parking_space(pks_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
	
CREATE TABLE equipment_info (
	category	      varchar(10) DEFAULT 'INDOOR' CHECK (category IN ('OUTDOOR', 'INDOOR')),
	equipment_type	varchar(64) NOT NULL,
	check_period	  numeric(3, 0) DEFAULT 1 CHECK (check_period > 0) ,
	repair_fee	    numeric(5, 2) DEFAULT 0 CHECK (repair_fee >= 0),
	PRIMARY KEY (category, equipment_type)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE equipment (
	equipment_id	  INT NOT NULL AUTO_INCREMENT,
	category	      varchar(10) NOT NULL,
	equipment_type	varchar(64) NOT NULL,
	description     varchar(1024) NOT NULL,
	PRIMARY KEY (equipment_id),
	FOREIGN KEY (category, equipment_type) REFERENCES equipment_info(category, equipment_type) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE equipment_record (
	community_id	INT NOT NULL,
	equipment_id	INT NOT NULL,
	PRIMARY KEY (community_id, equipment_id),
	FOREIGN KEY (community_id) REFERENCES community(community_id),
	FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE repair_order (
	repair_id	  INT NOT NULL AUTO_INCREMENT,
	resident_id	INT NOT NULL,
	equipment_id	INT NOT NULL,
	issue_date	timestamp NOT NULL,
	repair_reason	varchar(1024),
	processing	varchar(12) DEFAULT 'TODO' CHECK (processing in ('TODO', 'PROCESSING', 'DONE')),
	process_result	varchar(1024),
	PRIMARY KEY (repair_id),
	FOREIGN KEY (resident_id) REFERENCES resident(resident_id),
	FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE inspect_record (
	equipment_id		INT NOT NULL,
	issue_date		  timestamp NOT NULL,
	inspector		    varchar(255) NOT NULL,
	waiting_for_repair	varchar(5) DEFAULT 'NO' CHECK (waiting_for_repair IN ('YES', 'NO')),
	PRIMARY KEY (equipment_id, issue_date),
	FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE maintenance_record (
	equipment_id		INT NOT NULL,
	issue_date		  timestamp NOT NULL,
	maintenance_worker	varchar(255) NOT NULL,
	maintenance_result	varchar(1024),
	PRIMARY KEY (equipment_id, issue_date),
		FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE complaint_order (
	complaint_id	INT NOT NULL AUTO_INCREMENT,
	residence_id	INT NOT NULL,
	category	varchar(64),
	issue_date	timestamp NOT NULL,
	complaint_result varchar(1024),
	PRIMARY KEY (complaint_id),
	FOREIGN KEY (residence_id) REFERENCES residence(residence_id)
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
