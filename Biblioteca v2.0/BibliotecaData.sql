--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Data for Name: arhiva; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO arhiva (arhiva_id, arhiva_nume, arhiva_prenume, arhiva_varsta) VALUES (1, 'Ionescu                       ', 'Bianca                        ', 21);
INSERT INTO arhiva (arhiva_id, arhiva_nume, arhiva_prenume, arhiva_varsta) VALUES (2, 'Lascar                        ', 'Ion                           ', 14);
INSERT INTO arhiva (arhiva_id, arhiva_nume, arhiva_prenume, arhiva_varsta) VALUES (3, 'Vladulescu                    ', 'Georgiana                     ', 22);
INSERT INTO arhiva (arhiva_id, arhiva_nume, arhiva_prenume, arhiva_varsta) VALUES (4, 'Albu                          ', 'Ana                           ', 22);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (4, 'Mihail', 'Sadoveanu');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (5, 'Marin', 'Preda');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (6, 'Liviu', 'Radu');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (7, 'Ioan', 'Grigorescu');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (9, 'Liviu', 'Rebreanu');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (10, 'Ioan', 'Slavici');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (11, 'Ion', 'Ghica');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (12, 'Ion', 'Creanga');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (13, 'Ticu', 'Arhip');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (1, 'Mihai', 'Eminescuu');
INSERT INTO authors (author_id, author_firstname, author_lastname) VALUES (3, 'Mircea', 'Eliade');


--
-- Name: authors_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('authors_author_id_seq', 14, true);


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO books (book_id, book_author_id, book_title, book_nocopies, book_noborrowedcopies) VALUES (9, 9, 'Ion', 2, 1);
INSERT INTO books (book_id, book_author_id, book_title, book_nocopies, book_noborrowedcopies) VALUES (7, 5, 'Cel mai iubit dintre pamanteni', 4, 0);
INSERT INTO books (book_id, book_author_id, book_title, book_nocopies, book_noborrowedcopies) VALUES (8, 12, 'Povestea lui Harap Alb', 5, 0);
INSERT INTO books (book_id, book_author_id, book_title, book_nocopies, book_noborrowedcopies) VALUES (10, 3, 'Mitrey', 2, 0);


--
-- Name: books_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('books_book_id_seq', 10, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (user_id, user_email, user_firstname, user_lastname) VALUES (1, 'client1@example.com', 'Maria', 'Popa');


--
-- Data for Name: borrowedbooks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO borrowedbooks (borrowedbook_id, borrowedbook_user_id, borrowedbook_book_id, borrowedbook_date) VALUES (17, 1, 9, '2015-07-29');


--
-- Name: borrowedbooks_borrowedbook_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('borrowedbooks_borrowedbook_id_seq', 17, true);


--
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: exemplu1; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO exemplu1 (id, city) VALUES (1, 'Denver                        ');
INSERT INTO exemplu1 (id, city) VALUES (2, 'Caribu                        ');


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_user_id_seq', 1, true);


--
-- Data for Name: verificare; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- PostgreSQL database dump complete
--

