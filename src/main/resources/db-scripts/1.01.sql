CREATE SCHEMA `travelbook` DEFAULT CHARACTER SET utf8 ;

DROP TABLE IF EXISTS version;
CREATE TABLE IF NOT EXISTS version
(
    versionId  INT         NOT NULL AUTO_INCREMENT,
    number     VARCHAR(45) NULL,
    createTime DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`versionId`)
);

-- ----------------------
-- Version Stamp
-- ----------------------
INSERT INTO version (versionId, number) VALUES (1, '1.01');
