alter table user modify email varchar(70) null;

alter table user modify password varchar(100) null;

INSERT INTO location(location) VALUES ('Dhaka');
INSERT INTO location(location) VALUES ('CTG');
INSERT INTO location(location) VALUES ('Sylhet');
INSERT INTO location(location) VALUES ('Rajshahi');
INSERT INTO location(location) VALUES ('Coxs Bazar');
INSERT INTO location(location) VALUES ('Bandarban');

-- ----------------------
-- Version Stamp
-- ----------------------
INSERT INTO version (versionId, number) VALUES (3, '1.03');
