create table worker(
      id serial primary key,
      name varchar(255)
);

create table job(
      id serial primary key,
      name varchar(255),
      worker_id int references worker(id)
);
