CREATE TABLE authors
(
  author_id bigserial NOT NULL,
  author_firstname character varying(255) NOT NULL,
  author_lastname character varying(255),
  CONSTRAINT authors_pkey PRIMARY KEY (author_id)
)

create table books 
(
	book_id bigserial primary key, 
	book_author_id integer not null, 
	book_title varchar(255) not null, 
	book_noCopies integer not null, 
	book_noBorrowedCopies integer default 0,
	foreign key (book_author_id) references authors (author_id)
);

CREATE TABLE users
(
  user_id bigserial NOT NULL,
  user_email character varying(255) NOT NULL,
  user_firstname character varying(255),
  user_lastname character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (user_id)
)

CREATE TABLE borrowedBooks
(
	borrowedBook_id bigserial PRIMARY KEY,
	borrowedBook_user_id integer not null,
	borrowedBook_book_id integer not null,
	borrowed_date date not null, 
	foreign key (borrowedBook_user_id) references users (user_id),
	foreign key (borrowedBook_book_id) references books (book_id)
);

