CREATE TABLE useraccount (   
    username character varying(20) DEFAULT ''::character varying NOT NULL PRIMARY KEY,
    password character varying(20) DEFAULT ''::character varying NOT NULL,
    rol		 character varying(20) DEFAULT ''::character varying NOT NULL
);

INSERT INTO useraccount VALUES ('JustinWilkes', 'wachtwoord', 'user');