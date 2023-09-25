USE master
GO
IF EXISTS (SELECT * FROM sys.databases WHERE name like 'MookProject') DROP DATABASE MookProject
GO
CREATE DATABASE MookProject
GO
USE MookProject

-- CREATE TABLES
GO
-- employee
CREATE TABLE employee
(
	employeeId			BIGINT			IDENTITY,
	account				VARCHAR(50)		UNIQUE,
	department			NVARCHAR(50),
	employeeAddress		VARCHAR(50),
	employeeBirthdate	DATE,
	employeeEmail		VARCHAR(50),
	employeeName		NVARCHAR(50),
	employeePhone		VARCHAR(15),
	[password]			VARCHAR(20),
	sex					BIT,

	CONSTRAINT PK_employeeId PRIMARY KEY(employeeId),
)
GO
-- trip
CREATE TABLE trip
(
	tripId						BIGINT	IDENTITY,
	bookedTicketNumber			INT,
	carType						VARCHAR(50),
	departureDate				DATE,
	departureTime				TIME(0),
	destination					NVARCHAR(50),
	driver						NVARCHAR(50),
	maximumOnlineTicketNumber	INT,

	CONSTRAINT PK_tripId PRIMARY KEY(tripId)
)
GO
-- booking office
CREATE TABLE bookingoffice
(
	officeId				BIGINT	IDENTITY,
	endContractDeadline		DATE,
	officeName				NVARCHAR(50),
	officePhone				VARCHAR(15),
	officePlace				NVARCHAR(50),
	officePrice				MONEY,
	startContractDeadline	DATE,
	tripId					BIGINT,

	CONSTRAINT PK_officeId PRIMARY KEY(officeId),
	CONSTRAINT FK_tripId_BookingOffice FOREIGN KEY(tripId) REFERENCES trip(tripId)
)
GO
-- parking lot
CREATE TABLE parkinglot
(
	parkId		BIGINT	IDENTITY,
	parkArea	BIGINT,
	parkName	NVARCHAR(50),
	parkPlace	NVARCHAR(50),
	parkPrice	MONEY,
	parkStatus	NVARCHAR(50),

	CONSTRAINT PK_parkId PRIMARY KEY(parkId)
)
GO
-- car
CREATE TABLE car
(
	licensePlate	VARCHAR(50),
	carColor		NVARCHAR(11),
	carType			VARCHAR(50),
	company			VARCHAR(50),
	parkId			BIGINT,

	CONSTRAINT PK_licensePlate PRIMARY KEY(licensePlate),
	CONSTRAINT FK_parkId FOREIGN KEY(parkId) REFERENCES parkinglot(parkId)
)
GO
-- ticket
CREATE TABLE ticket
(
	ticketId			BIGINT	IDENTITY,
	bookingTime			TIME(0),
	customerName		NVARCHAR(50),
	licensePlate		VARCHAR(50),
	tripId				BIGINT,

	CONSTRAINT PK_ticketId PRIMARY KEY(ticketId),
	CONSTRAINT FK_tripId_Ticket FOREIGN KEY(tripId) REFERENCES trip(tripId),
	CONSTRAINT FK_licensePlate FOREIGN KEY(licensePlate) REFERENCES car(licensePlate)
)

-- INSERT RECORD TO TABLE
GO
-- insert to employee table
INSERT INTO employee
(account,	department,			employeeAddress,		employeeBirthdate,	employeeEmail,			employeeName,		employeePhone,	[password],	sex) VALUES
('admin',	'HRM',			N'Bac Kan, Viet Nam',	N'2000-01-08',		'admin@gmail.com',		N'ADMIN',			'0365156888',	'123',		0),
('HoangVT',	'CPO',			N'Ha Noi, Việt Nam',	N'2000-01-02',		'HoangVT@gmail.com',		N'Vu Tuan Hoang',	'0123456789',	'123',		1),
('LongDH',	'CPO',			N'Ha Noi, Việt Nam',	N'2000-02-03',		'LongDH@gmail.com',		N'Dao Hoang Long',	'0369852147',	'123',		1),
('HuyND',	'CPO',			N'Bac Ninh, Việt Nam',	N'2000-03-04',		'HuyND@gmail.com',		N'Nguyen Dinh Huy',	'0987456321',	'123',		1),
('HuyDX',	'TRIP',				N'HCM, Việt Nam',	N'2000-04-05',		'HuyDX@gmail.com',	N'Dong Xuan Huy',		'0546321987',	'123',		0)
GO
-- insert to trip table
INSERT INTO trip
(bookedTicketNumber,	carType,				departureDate,	departureTime,	destination,	driver,				maximumOnlineTicketNumber)VALUES
(1,						N'Xe Cong Ty',	N'2022-04-09',	'12:00',		N'Bac Ninh',	'Nguyen Van A',	30),
(2,						N'Xe Khach',	N'2022-04-09',	'10:00',		N'Bac Giang',	'Nguyen Van B', 31),
(3,						N'Xe Cuu Hoa',	N'2022-04-09',	'09:00',		N'Ha Noi',		'Nguyen Van C', 32)
GO
-- booking office
INSERT INTO bookingoffice
(endContractDeadline,	officeName,			officePhone,	officePlace,	officePrice,	startContractDeadline,	tripId) VALUES
('2022-01-02',			N'Diamond Car',		'0147852357',	'Quay so 1',	90000,			'2021-12-12',			1),
('2022-02-03',			N'Platinum Car',		'0369852159',	'Quay so 2',	120000,			'2022-01-02',			2),
('2022-03-04',			N'Gold Car',		'0147852147',	'Quay so 3',	100000, '		2022-02-03',			3)
GO
-- parking lot
INSERT INTO parkinglot
(parkArea, parkName, parkPlace, parkPrice, parkStatus)VALUES
(20, 'Bai so 1', 'Khu Dong', 250000, 'Blank'),
(30, 'Bai so 2', 'Khu Nam', 350000, 'Blank'),
(90, 'Bai so 3', 'Khu Bac', 450000, 'Blank')
GO
-- car
INSERT INTO car
(licensePlate,carColor,carType,company,parkId)VALUES
('29K-240599', 'Bac', 'HUYNDAI', 'Hoang Long', 1),
('29A-240599', 'Do', 'Toyota', 'Phuong Trang', 2),
('29B-240599', 'Tim', 'Dream', 'Cam Van', 3)

GO
-- ticket
INSERT INTO ticket
(bookingTime,customerName,licensePlate,tripId)VALUES
('11:00', 'Vu Tuan Hoang', '29K-240599', 1),
('9:05', 'Nguyen Van A', '29A-240599', 2),
('8:20', 'Nguyen Van B', '29B-240599', 3)
