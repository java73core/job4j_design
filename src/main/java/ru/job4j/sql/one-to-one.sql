create table book(
      id serial primary key,
      name varchar(255)
);

create table author(
      id serial primary key,
      name varchar(255)
);

create table book_author(
      id serial primary key,
      book_id int references author(id),
      author_id int references book(id)
);