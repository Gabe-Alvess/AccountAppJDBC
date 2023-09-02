show databases;
create database accountapp;
use accountapp;

create table user_tb (
  id int primary key,
  fname varchar(50),
  lname varchar(50),
  accEmail  varchar(50),
  
  foreign key(accEmail) references account_tb(email)
);

create table account_tb (
	email varchar(50) primary key,
    passw varchar(50) not null
);

show tables;

describe user;

drop table account;

INSERT INTO account values('jean@jean.jean', 'jean123');
INSERT INTO user values('4029194', 'Jean', 'Jaccques', 'jean@jean.jean');

select * from account;
select * from user;

create user 'Gabriel Alves'@'localhost' identified by '14789632';

grant all privileges on accountapp.* to 'Gabriel Alves'@'localhost';

flush privileges;