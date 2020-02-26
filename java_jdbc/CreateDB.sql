USE [BankDB]
GO

CREATE TABLE [User](
	id bigint NOT NULL PRIMARY KEY IDENTITY(1, 1),
	[login] varchar(20) NOT NULL UNIQUE,
	[password] varchar(30) NOT NULL,
	[address] varchar(max) NOT NULL,
	phone varchar(30) NOT NULL,
);
GO

CREATE TABLE Account(
	id bigint NOT NULL PRIMARY KEY IDENTITY(1, 1),
	idUser bigint NOT NULL FOREIGN KEY REFERENCES [User](id),
	amount float NOT NULL default 0.0,
	accCode varchar(3) NOT NULL,
	defAccount bit NOT NULL default 0, 
);
GO


CREATE TABLE Operation(
		id BIGINT NOT NULL PRIMARY KEY IDENTITY(1, 1),
		dateOfOperation date NOT NULL,
		accCode varchar(3) NOT NULL,
		accIn bigint NOT NULL FOREIGN KEY REFERENCES Account(id),
		accOut bigint NOT NULL FOREIGN KEY REFERENCES Account(id),
		amount float NOT NULL,
		amountBefore float NOT NULL,
		amountAfter float NOT NULL,
);
GO


drop table Operation
drop table Account
drop table [User]
