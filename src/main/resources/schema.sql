CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    name        VARCHAR(100) NOT NULL,
    full_name   VARCHAR(250) NULL,
    inn         VARCHAR(12)  NOT NULL,
    kpp         VARCHAR(9)  NOT NULL,
    address     VARCHAR(1000)  NOT NULL,
    phone       VARCHAR(11)  NULL,
    is_active    BOOLEAN  NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    org_id      INTEGER NOT NULL,
    name        VARCHAR(100) NOT NULL,
    address     VARCHAR(1000)  NOT NULL,
    phone       VARCHAR(11)  NULL,
    is_active   BOOLEAN  NOT NULL
);

CREATE TABLE IF NOT EXISTS User (
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL,
    office_id       INTEGER  NULL,
    doc_id          INTEGER  NULL,
    country_id      INTEGER  NULL,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NULL,
    middle_name     VARCHAR(100)  NULL,
    phone           VARCHAR(11)  NULL,
    position        VARCHAR(100)  NOT NULL,
    is_identified   BOOLEAN  NOT NULL
);

  CREATE TABLE IF NOT EXISTS Document_Type (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    doc_code    VARCHAR(3) NOT NULL,
    doc_name    VARCHAR(100) NULL
);

CREATE TABLE IF NOT EXISTS Document (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    doc_type_id INTEGER  NOT NULL,
    doc_number  VARCHAR(10)  NOT NULL,
    doc_date    DATE  NOT NULL
);

CREATE TABLE IF NOT EXISTS Country (
    id                INTEGER PRIMARY KEY AUTO_INCREMENT,
    version           INTEGER NOT NULL,
    code  VARCHAR(100) NOT NULL,
    name  VARCHAR(250) NULL
);

CREATE INDEX IX_Office_Org_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_Doc_Id ON User (doc_id);
ALTER TABLE User ADD FOREIGN KEY (doc_id) REFERENCES Document(id);

CREATE INDEX IX_User_Country_Id ON User (country_id);
ALTER TABLE User ADD FOREIGN KEY (country_id) REFERENCES Country(id);

CREATE UNIQUE INDEX UX_Document_Type_Doc_Code ON Document_Type (doc_code);

CREATE UNIQUE INDEX UX_Country_Code ON Country (code);

CREATE INDEX IX_Document_Doc_Type_Id ON Document (doc_type_id);
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Document_Type(id);