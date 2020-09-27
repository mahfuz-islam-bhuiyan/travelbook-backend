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


DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    userId         INT          NOT NULL AUTO_INCREMENT,
    email          VARCHAR(45)  NULL,
    password       VARCHAR(45)  NULL,
    name           VARCHAR(100) NULL,
    createTime     DATETIME     NULL          DEFAULT CURRENT_TIMESTAMP,
    updateTime     DATETIME     NULL          DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_pk
        PRIMARY KEY (userId)
);

CREATE UNIQUE INDEX user_email_uindex
    ON user (email);

DROP TABLE IF EXISTS users_status;

CREATE TABLE IF NOT EXISTS users_status
(
    userStatusId      INT          NOT NULL AUTO_INCREMENT,
    userId            INT          NOT NULL,
    statusMessage     VARCHAR(100) NULL,
    privacyStatusType ENUM ('PUBLIC', 'PRIVATE') default 'PUBLIC' NOT NULL,
    locationId        INT          NOT NULL,
    isPinned          TINYINT(1)  NULL,
    createTime        DATETIME     NULL          DEFAULT CURRENT_TIMESTAMP,
    updateTime        DATETIME     NULL          DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_status_pk
        PRIMARY KEY (userStatusId)
);

DROP TABLE IF EXISTS location;
CREATE TABLE IF NOT EXISTS location
(
    locationId INT         NOT NULL AUTO_INCREMENT,
    location   VARCHAR(45) NULL,
    createTime DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    updateTime DATETIME    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`locationId`)
);


-- ----------------------
-- Version Stamp
-- ----------------------
INSERT INTO version (versionId, number)
VALUES (2, '1.02');

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

