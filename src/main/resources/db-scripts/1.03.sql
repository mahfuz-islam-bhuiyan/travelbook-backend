alter table user modify email varchar(70) null;

alter table user modify password varchar(100) null;

-- ----------------------
-- Version Stamp
-- ----------------------
INSERT INTO version (versionId, number) VALUES (3, '1.03');
