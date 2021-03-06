--DROP Table Customer;
DROP TABLE Transactions;
DROP TABLE DemandQueue;
DROP TABLE Journey;
Drop TABLE Demands;
Drop TABLE Users;
Drop TABLE Customer;
Drop TABLE Drivers;

CREATE TABLE Customer (
  id int not null GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Name varchar(20),
  Address varchar(60),
  PRIMARY KEY (id)
);


INSERT INTO Customer (Name, Address) VALUES
('Eva Smith', '129 Station Rd, London, N3 2AS'),
('Rob Manton', '23 Bow Lane, London, N3'),
('Bob Currie', '54 Teignmouth Rd, London, NW2'),
('Jim Hunter', '765 High Road, London, N12'),
('Phil Johnson', '75 Squires Lane, London, N3'),
('Saim Soyler', '2 Rosemary Ave, London, N3'),
('Gul Hikmet', '31 Clifton Rd, London, N3 2SG');

-- --------------------------------------------------------
--DROP Table Demands;
CREATE TABLE Demands (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Name varchar(20),
  Address varchar(60),
  Destination varchar(60),
  Date date DEFAULT NULL,
  Time time DEFAULT NULL,
  Status varchar(15) NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO Demands (Name, Address, Destination, Date, Time, Status) VALUES
('M. E. Aydin', 'Finchley, London', 'King''s Cross, London', '2015-11-02', '09:22:18', 'Outstanding');

-- --------------------------------------------------------
--DROP Table Drivers;
CREATE TABLE Drivers (
  Registration varchar(10) NOT NULL,
  Name varchar(20),
  PRIMARY KEY (Registration)
);


INSERT INTO Drivers (Registration, Name) VALUES
('AK52VZV', 'John Smith'),
('BN60WKA', 'Mehmet Aydin'),
('R34AKP', 'Mark Johnson');

-- --------------------------------------------------------

--DROP Table Journey;
CREATE TABLE Journey (
  jid int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  id int NOT NULL,
  Destination varchar(60),
  Distance integer NOT NULL DEFAULT 1,
  Registration varchar(10) NOT NULL,
  Date date NOT NULL,
  Time time DEFAULT NULL,
  PRIMARY KEY (jid)
);

Alter table Journey add foreign key (id) references Customer;
Alter table Journey add foreign key (Registration) references Drivers;

INSERT INTO Journey (Destination, Distance, id, Registration, Date, Time) VALUES
('King''s Cross Station, London', 5, 1, 'BN60WKA', '2015-10-14', '09:30:00'),
('Heathrow Terminal 3, London', 20, 7, 'BN60WKA', '2015-10-14', '12:00:00'),
('120 Green Lanes, London, N13', 7, 7, 'AK52VZV', '2015-10-15', '06:00:00'),
('131 Stoke Newington High Road, London, N12', 8, 7, 'AK52VZV', '2015-10-15', '12:00:00'),
('Luton Airport, Luton', 30, 1, 'R34AKP', '2015-10-22', '10:00:00');

CREATE TABLE Users (
   id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   email varchar(300) NOT NULL UNIQUE,
   password varchar(300) NOT NULL,
   type varchar(10) NOT NULL,
   cid int,
   did varchar(10),
   FOREIGN KEY (cid) REFERENCES Customer (id),
   FOREIGN KEY (did) REFERENCES Drivers (Registration)
);

INSERT INTO Users (email, password, type, cid, did) VALUES
('evasmith@example.com', 'test123', 'customer', 1, NULL),
('robmanton@example.com', 'test123', 'customer', 2, NULL),
('bobcurrie@example.com', 'test123', 'customer', 3, NULL),
('jimhunter@example.com', 'test123', 'customer', 4, NULL),
('philjohnson@example.com', 'test123', 'customer', 5, NULL),
('saimsoyler@example.com', 'test123', 'customer', 6, NULL),
('gulhikmet@example.com', 'test123', 'customer', 7, NULL),
('johnsmith@example.com', 'driver123', 'driver', NULL, 'AK52VZV'),
('mehmetaydin@example.com', 'driver123', 'driver', NULL, 'BN60WKA'),
('markjohnson@example.com', 'driver123', 'driver', Null, 'R34AKP'),
('zain@example.com', 'admin123', 'admin', NULL, NULL),
('nasru@example.com', 'admin123', 'admin', NULL, NULL),
('shaaik@example.com', 'admin123', 'admin', NULL, NULL),
('shimaanath@example.com', 'admin123', 'admin', NULL, NULL),
('imma@example.com', 'admin123', 'admin', NULL, NULL);


CREATE TABLE Transactions (
   id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   journey_id int NOT NULL,
   transaction_date TIMESTAMP NOT NULL,
   amount FLOAT NOT NULL,
   FOREIGN KEY (journey_id) REFERENCES Journey (jid)
);

CREATE TABLE DemandQueue (
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    demand_id int NOT NULL UNIQUE,
    customer_id int NOT NULL UNIQUE,
    driver_id varchar(10) UNIQUE,
    FOREIGN KEY (demand_id) REFERENCES Demands (id),
    FOREIGN KEY (customer_id) REFERENCES Customer (id),
    FOREIGN KEY (driver_id) REFERENCES Drivers (registration),
    PRIMARY KEY (id)
);
