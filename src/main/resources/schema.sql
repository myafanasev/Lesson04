CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username text COLLATE pg_catalog."default" NOT NULL,
    fio text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS logins
(
    id bigint NOT NULL DEFAULT nextval('logins_id_seq'::regclass),
    access_date timestamp without time zone NOT NULL,
    user_id bigint NOT NULL,
    application text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT logins_pkey PRIMARY KEY (id),
    CONSTRAINT logins_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);