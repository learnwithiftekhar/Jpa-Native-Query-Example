create table books (
                       id bigint not null auto_increment,
                       author varchar(255),
                       genre varchar(255),
                       price float(53),
                       title varchar(255),
                       primary key (id)
) engine=InnoDB;

CREATE FULLTEXT INDEX idx_book_title_author_genre ON books (title, author, genre);