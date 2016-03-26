
drop table cashier_sold;
drop table machine_sold;
drop table online_sold;
drop table ticket_machine;
drop table ticket;
drop table isPlaying;
drop table manager;
drop table cashier;
drop table employee;
drop table Seat;
drop table Auditorium;
drop table directed_by;
drop table performed_by;
drop table director;
drop table actor;
drop table Movie;
drop table member;
drop table customer;


create table customer
(cname varchar(30) not null,
cphone char(10) not null,
primary key (cname, cphone),
unique (cphone));

create table member
(cname varchar(30) not null,
cphone char(10) not null,
member_num char(6) not null,
reward_point integer not null,
primary key (cname, cphone),
foreign key (cname, cphone) references customer,
unique (member_num));


create table Movie(
  title varchar(40) null,
  genre varchar(10) null,
  language varchar(10)  null,
  length int not null,
  movie_ID char(6) not null,
  rating char(4) null,
  primary key(movie_ID)
);

create table actor
(aname varchar(30) not null,
agender varchar(6) not null,
primary key (aname));

create table director
(dname varchar(30) not null,
dgender varchar(6) not null,
primary key (dname));


create table performed_by (
aname varchar(30) not null,
perform_year char(4) null,
movie_ID char(6) not null,
primary key (aname, movie_ID),
foreign key(movie_ID) references Movie,
foreign key(aname) references actor);

create table directed_by (
dname varchar(30) not null,
direct_year char(4) null,
movie_ID char(6) not null,
primary key (dname, movie_ID),
foreign key(movie_ID) references Movie,
foreign key(dname) references director);


create table Auditorium(
  capacity int not null,
  audi_num int not null,
  availabilty char not null,
  type char(4) not null,
  primary key (audi_num)  
);

create table Seat(
  seat_num char(3) not null,
  audi_num int not null,
  primary key(audi_num, seat_num),
  foreign key(audi_num) references Auditorium  
);

create table employee
(ename varchar(20) not null,
ephone char(10) not null,
eid char(8) not null,
salary integer null,
primary key (eid));

create table cashier
(eid char(8) not null,
hourly_rate integer not null,
hours integer not null,
primary key (eid),
Constraint fk_cash_employee 
    foreign key (eid) 
    references employee(eid) 
    on delete cascade,
check (hours <=36));

create table manager
(eid char(8) not null,
contractid integer not null,
primary key (eid),
CONSTRAINT fk_man_employee foreign key (eid) references employee(eid));

create table ticket_machine
(machineID char(4) not null,
since integer null,
location char(50) not null,
primary key(machineID));

create table isPlaying(
    show_time varchar(20) not null,
    show_date varchar(20) not null,
    format char(4) not null,
    audi_num int not null,
    movie_ID char(6) not null,
    eid char(8) not null,
    primary key(show_date,show_time,audi_num,movie_ID),
    foreign key (audi_num) references Auditorium,
    foreign key (eid) references Manager,
    foreign key (movie_ID) references Movie
);

create table ticket (
	ticket_num char(5) not null,
	cname varchar(30) null,
	cphone char(10) null,
	price int null,
	isSold int default 0,
	show_time varchar(20) not null,
	show_date varchar(20) not null,
	seat_num char(3) not null,
	audi_num int not null,
	movie_ID char(6) not null,
	primary key(ticket_num),
	foreign key (show_date,show_time,audi_num,movie_ID) references isPlaying,
	foreign key (audi_num, seat_num) references seat,
	foreign key (cname, cphone) references customer);


create table online_sold
(ticket_num char(5) not null,
conf_code char(12) not null,
primary key(ticket_num),
foreign key(ticket_num) references ticket);

create table cashier_sold
(ticket_num char(5) not null,
eid char(8) not null,
primary key(ticket_num),
foreign key(ticket_num) references ticket);

create table machine_sold
(ticket_num char(5) not null,
machineID char(4) not null,
primary key(ticket_num),
foreign key(ticket_num) references ticket,
foreign key(machineID) references ticket_machine);








