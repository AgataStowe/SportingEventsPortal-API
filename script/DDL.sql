-------------------------------------------SCHEMA---------------------------------------------
CREATE SCHEMA IF NOT EXISTS event
    AUTHORIZATION postgres;

COMMENT ON SCHEMA event
    IS 'event schema';

GRANT ALL ON SCHEMA event TO PUBLIC;

GRANT ALL ON SCHEMA event TO postgres;

-------------------------------------------EVENT.ADDRESS---------------------------------------------
CREATE TABLE IF NOT EXISTS event.address
(
    id_address bigint NOT NULL,
    zip_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    neighborhood character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer,
    public_area character varying(255) COLLATE pg_catalog."default" NOT NULL,
    state character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id_address)
);

COMMENT ON TABLE event.address IS 'Address table';

COMMENT ON COLUMN event.address.id_address IS 'Unique table identifier';

COMMENT ON COLUMN event.address.zip_code IS 'ZIP code';

COMMENT ON COLUMN event.address.city IS 'Name of city';

COMMENT ON COLUMN event.address.neighborhood IS 'Name of neighborhood';

COMMENT ON COLUMN event.address.number IS 'Number';

COMMENT ON COLUMN event.address.public_area IS 'Name of public_area';

COMMENT ON COLUMN event.address.state IS 'Name of state';

ALTER TABLE IF EXISTS event.address
    OWNER to postgres;

-------------------------------------------EVENT.EVENTS---------------------------------------------
CREATE TABLE IF NOT EXISTS event.events
(
    id_event bigint NOT NULL,
    active boolean DEFAULT true,
    creation_date date DEFAULT CURRENT_DATE,
    date_time timestamp without time zone NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    id_address bigint,
    CONSTRAINT pk_events PRIMARY KEY (id_event),
    CONSTRAINT pk_events_add FOREIGN KEY (id_address)
        REFERENCES event.address (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

COMMENT ON TABLE event.events IS 'Address table';

COMMENT ON COLUMN event.events.id_event IS 'Unique table identifier';

COMMENT ON COLUMN event.events.active IS 'Active/inactive indicator';

COMMENT ON COLUMN event.events.creation_date IS 'Creation date of the event';

COMMENT ON COLUMN event.events.date_time IS 'Date and time of the event';

COMMENT ON COLUMN event.events.description IS 'Description of the event';

COMMENT ON COLUMN event.events.id_address IS 'Unique table identifier of Address';

ALTER TABLE IF EXISTS event.events
    OWNER to postgres;
	
-------------------------------------------EVENT.USERS---------------------------------------------
CREATE TABLE IF NOT EXISTS event.users
(
    id_user bigint NOT NULL,
    active boolean DEFAULT true,
    creation_date date DEFAULT CURRENT_DATE,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nickname character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id_user)
);

COMMENT ON TABLE event.users IS 'Users table';

COMMENT ON COLUMN event.users.id_user IS 'Unique table identifier';

COMMENT ON COLUMN event.users.active IS 'Active/inactive indicator';

COMMENT ON COLUMN event.users.creation_date IS 'Creation date of the event';

COMMENT ON COLUMN event.users.email IS 'User email address';

COMMENT ON COLUMN event.users.full_name IS 'User full name';

COMMENT ON COLUMN event.users.nickname IS 'User nickname';

COMMENT ON COLUMN event.users.password IS 'User password';

ALTER TABLE IF EXISTS event.users
    OWNER to postgres;
	
-------------------------------------------EVENT.EVENTS_USERS---------------------------------------------
CREATE TABLE IF NOT EXISTS event.events_users
(
    id_event bigint NOT NULL,
    id_user bigint NOT NULL,
    CONSTRAINT events_users_users FOREIGN KEY (id_user)
        REFERENCES event.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT events_users_events FOREIGN KEY (id_event)
        REFERENCES event.events (id_event) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE event.events_users IS 'Rlationship table between event and users';

COMMENT ON COLUMN event.events_users.id_event IS 'Unique table identifier of event';

COMMENT ON COLUMN event.events_users.id_user IS 'Unique table identifier of users';

ALTER TABLE IF EXISTS event.events_users
    OWNER to postgres;

