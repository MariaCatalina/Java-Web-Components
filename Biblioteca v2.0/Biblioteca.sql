--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: arhiva; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authors (
    author_id bigint NOT NULL,
    author_firstname character varying(255) NOT NULL,
    author_lastname character varying(255)
);

ALTER TABLE authors OWNER TO postgres;

--
-- Name: authors_author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE authors_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authors_author_id_seq OWNER TO postgres;

--
-- Name: authors_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE authors_author_id_seq OWNED BY authors.author_id;


--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE books (
    book_id bigint NOT NULL,
    book_author_id integer NOT NULL,
    book_title character varying(255) NOT NULL,
    book_nocopies integer NOT NULL,
    book_noborrowedcopies integer DEFAULT 0
);


ALTER TABLE books OWNER TO postgres;

--
-- Name: books_book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE books_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE books_book_id_seq OWNER TO postgres;

--
-- Name: books_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE books_book_id_seq OWNED BY books.book_id;


--
-- Name: borrowedbooks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE borrowedbooks (
    borrowedbook_id bigint NOT NULL,
    borrowedbook_user_id integer NOT NULL,
    borrowedbook_book_id integer NOT NULL,
    borrowedbook_date date NOT NULL
);


ALTER TABLE borrowedbooks OWNER TO postgres;

--
-- Name: borrowedbooks_borrowedbook_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE borrowedbooks_borrowedbook_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE borrowedbooks_borrowedbook_id_seq OWNER TO postgres;

--
-- Name: borrowedbooks_borrowedbook_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE borrowedbooks_borrowedbook_id_seq OWNED BY borrowedbooks.borrowedbook_id;


--
-- Name: contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--


CREATE TABLE users (
    user_id bigint NOT NULL,
    user_email character varying(255) NOT NULL,
    user_firstname character varying(255),
    user_lastname character varying(255)
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;


--
-- Name: verificare; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE verificare (
);


ALTER TABLE verificare OWNER TO postgres;

--
-- Name: author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY authors ALTER COLUMN author_id SET DEFAULT nextval('authors_author_id_seq'::regclass);


--
-- Name: book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY books ALTER COLUMN book_id SET DEFAULT nextval('books_book_id_seq'::regclass);


--
-- Name: borrowedbook_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY borrowedbooks ALTER COLUMN borrowedbook_id SET DEFAULT nextval('borrowedbooks_borrowedbook_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq'::regclass);


--
-- Name: arhiva_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arhiva
    ADD CONSTRAINT arhiva_pkey PRIMARY KEY (arhiva_id);


--
-- Name: authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);

--
-- Name: books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);

--
-- Name: borrowedbooks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY borrowedbooks
    ADD CONSTRAINT borrowedbooks_pkey PRIMARY KEY (borrowedbook_id);

--
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: books_book_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_book_author_id_fkey FOREIGN KEY (book_author_id) REFERENCES authors(author_id);

--
-- Name: borrowedbooks_borrowedbook_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY borrowedbooks
    ADD CONSTRAINT borrowedbooks_borrowedbook_book_id_fkey FOREIGN KEY (borrowedbook_book_id) REFERENCES books(book_id);

--
-- Name: borrowedbooks_borrowedbook_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY borrowedbooks
    ADD CONSTRAINT borrowedbooks_borrowedbook_user_id_fkey FOREIGN KEY (borrowedbook_user_id) REFERENCES users(user_id);

--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;

--
-- PostgreSQL database dump complete
--

