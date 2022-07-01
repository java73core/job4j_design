 create table books(
     id serial primary key,
     name varchar(255)
 );

 create table libraries(
     id serial primary key,
     name varchar(255)
 );

 create table books_libraries(
     id serial primary key,
     books_id int references books(id),
     libraries_id int references libraries(id)
 );