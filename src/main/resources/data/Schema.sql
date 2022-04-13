CREATE TABLE DRONES
	(
		SERIAL_NUMBER  VARCHAR(100) NOT NULL,
		MODEL VARCHAR(50) NOT NULL,
		WEIGHT_LIMIT INTEGER  NOT NULL,
		BATTERY_CAPACITY INTEGER NOT NULL,
		STATE VARCHAR(50) NOT NULL,
		PRIMARY KEY(SERIAL_NUMBER)
	);