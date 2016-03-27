
--customer
insert into customer
values('John Snow', '7782689039');

insert into customer
values('Arya Lannister', '6042931750');

insert into customer
values('Jamie Baratheon', '7782745729');

insert into customer
values('Tywin Greyjoy', '6042765729');

insert into customer
values('Leon Stark', '7783451672');

insert into customer
values('Paul Ludovic', '7787774309');

insert into customer
values('Harry Nozic', '7781112239');

insert into customer
values('Sashimi Tataki', '6048691038');


--member
insert into member
values('Arya Lannister', '6042931750', '274967', '120');

insert into member
values('Harry Nozic', '7781112239', '294758', '60');

insert into member
values('Paul Ludovic', '7787774309', '098344', '430');

insert into member
values('Tywin Greyjoy', '6042765729', '399920', '60');

insert into member
values('Sashimi Tataki', '6048691038', '183949', '50');


--Movie
insert into Movie
values('Mamamia','comedy','ENG','112','104112','PG');

insert into Movie
values('Killer in the Class','violence','ENG','176','344201','NC17');

insert into Movie
values('Titanic','romance','ENG','160','098344','R');

insert into Movie
values('Active Coding Life','action','FREN','120','399920','PG13');

insert into Movie
values('Ma Nerdy Cody Fam','family','FREN','63','120002','G');

insert into Movie
values('Upon Awakening','violence','JPN','142','540222','R');

insert into Movie
values('Mary the Dog','family','JPN','142','760232','PG13');

insert into Movie
values('Tree House','comedy','CHN','142','943322','G');


--actor
insert into actor
values('Ian Holm','M');

insert into actor
values('Bill Murray','M');

insert into actor
values('Samantha Morton','F');

insert into actor
values('Anne Hathaway','F');

insert into actor
values('Leonardo DiCaprio','M');

--director
insert into director
values('Atom Egoyan','M');

insert into director
values('Sofia Coppola','F');

insert into director
values('Alisa Potter', 'F');

insert into director
values('Lynne Ramsay','F');

insert into director
values('Jonathan Demme','M');

insert into director
values('Alejandro Inarritu','M');

--performed_by
insert into performed_by
values('Ian Holm', '1997', '104112');

insert into performed_by
values('Bill Murray', '2003', '098344');

insert into performed_by
values('Samantha Morton', '2003', '399920');

insert into performed_by
values('Anne Hathaway', '2008', '344201');

insert into performed_by
values('Leonardo DiCaprio', '2015', '120002');

--directed_by
insert into directed_by
values('Atom Egoyan', '1997', '104112');

insert into directed_by
values('Alisa Potter', '1997', '104112');

insert into directed_by
values('Sofia Coppola', '2003', '098344');

insert into directed_by
values('Lynne Ramsay', '2003', '399920');

insert into directed_by
values('Jonathan Demme', '2008', '344201');

insert into directed_by
values('Alejandro Inarritu', '2015', '120002');

--Auditorium
insert into Auditorium
values('50','1','0','IMAX');

insert into Auditorium
values('50','2','1','DBOX');

insert into Auditorium
values('200','8','0','UAVX');

insert into Auditorium
values('300','5','1','NORM');

insert into Auditorium
values('300','9','0','NORM');


--Seat
insert into Seat
values('J12','1');

insert into Seat
values('K09','2');

insert into Seat
values('A09','8');

insert into Seat
values('F03','5');

insert into Seat
values('K10','2');

insert into Seat
values('B01', '8');

insert into Seat
values('B02', '8');

insert into Seat
values('B03', '8');

insert into Seat
values('B04', '8');

insert into Seat
values('J11', '8');

insert into Seat
values('J12', '8');

insert into Seat
values('J10', '2');

insert into Seat
values('J11', '2');

insert into Seat
values('J12', '2');

insert into Seat
values('C09', '8');

insert into Seat
values('C10', '8');

insert into Seat
values('C11', '8');

insert into Seat
values('C11', '9');


--employee
insert into employee
values('Mary Jones','6048828812','22345542', NULL);

insert into employee
values('Pamillia Chen','7784002993','87765554', NULL);

insert into employee
values('Louis Lock','6049998847','90988886',NULL);

insert into employee
values('Peter Vermillion','6043822281','33420010','25000');

insert into employee
values('Udine Lau','6042877780','22234122','32000');

insert into employee
values('Larry You', '6045398882','12354455',NULL);

insert into employee
values('Bob Johnson', '7789928832', '33544887',NULL);

insert into employee
values('Ian Lobster', '7784431221', '34432122','34000');

--cashier
insert into cashier
values('22345542','8','12');

insert into cashier
values('87765554','8','20');

insert into cashier
values('12354455','9','20');

insert into cashier
values('33544887','8','18');

insert into cashier
values('90988886','9','15');

--manager
insert into manager
values('33420010','310103');

insert into manager
values('22234122','322102');

insert into manager
values('34432122','653233');

--ticket_machine
insert into ticket_machine
values('0119','2014','Richmond');

insert into ticket_machine
values('0012','1997','Vancouver');

insert into ticket_machine
values('0001','1993','Vancouver');

insert into ticket_machine
values('0002','1993','Burnaby');

insert into ticket_machine
values('0200','2016', 'Coquitlam');


--isPlaying
insert into isPlaying
values('12:45','12/2/2015','IMAX','1','344201','33420010');

insert into isPlaying
values('21:00','1/23/2016','DBOX','2','104112','22234122');

insert into isPlaying
values('9:20','1/23/2016','UAVX','8','098344','33420010');

insert into isPlaying
values('15:45','3/22/2016','NORM','5','399920','34432122');

insert into isPlaying
values('15:45','3/22/2016','DBOX','2','104112','22234122');

insert into isPlaying
values('9:20','1/23/2016','NORM','9','120002','22234122');

insert into isPlaying
values('10:17', '1/23/2016', 'NORM', '9','120002','22234122');

insert into isPlaying
values('17:02','2/02/2016','NORM','9','540222','22234122');

insert into isPlaying
values('3:43', '4/12/2016', 'NORM','9','760232','22234122');

insert into isPlaying
values('12:32', '4/12/2016', 'NORM','9','760232','22234122');

insert into isPlaying
values('22:30', '1/03/2016', 'NORM','9','943322','22234122');


--ticket
insert into ticket
values('11001', 'John Snow', '7782689039', '10.75', '1', '12:45', '12/2/2015', 'J12', '1', '344201');

insert into ticket
values('11020', NULL, NULL, '10.75', '0', '21:00', '1/23/2016', 'K09', '2', '104112');

insert into ticket
values('23123', 'Harry Nozic', '7781112239', '12.75', '1', '9:20', '1/23/2016', 'A09', '8', '098344');

insert into ticket
values('01098', 'Paul Ludovic', '7787774309', '10.75', '1', '15:45', '3/22/2016', 'F03', '5', '399920');

insert into ticket
values('33167', NULL, NULL, '12.75', '0', '15:45', '3/22/2016', 'K10', '2', '104112');

insert into ticket
values('21123', 'Tywin Greyjoy', '6042765729', '12.75', '1', '15:45', '3/22/2016', 'J10', '2', '104112');

insert into ticket
values('29470', 'Tywin Greyjoy', '6042765729', '12.75', '1', '15:45', '3/22/2016', 'J11', '2', '104112');

insert into ticket
values('36766', 'Tywin Greyjoy', '6042765729', '12.75', '1', '15:45', '3/22/2016', 'J12', '2', '104112');

insert into ticket
values('11084', 'Arya Lannister', '6042931750', '12.75', '1', '9:20', '1/23/2016', 'B01', '8', '098344');

insert into ticket
values('23162', 'Arya Lannister', '6042931750', '12.75', '1', '9:20', '1/23/2016', 'B02', '8', '098344');

insert into ticket
values('01037', 'Arya Lannister', '6042931750', '12.75', '1', '9:20', '1/23/2016', 'B03', '8', '098344');

insert into ticket
values('37466', 'Arya Lannister', '6042931750', '12.75', '1', '9:20', '1/23/2016', 'B04', '8', '098344');

insert into ticket
values('11456', 'Sashimi Tataki', '6048691038', '12.75', '1', '9:20', '1/23/2016', 'J11', '8', '098344');

insert into ticket
values('11034', 'Sashimi Tataki', '6048691038', '12.75', '1', '9:20', '1/23/2016', 'J12', '8', '098344');

insert into ticket
values('11035', NULL, NULL, '12.75', '0', '9:20', '1/23/2016', 'C09', '8', '098344');

insert into ticket
values('11036', NULL, NULL, '12.75', '0', '9:20', '1/23/2016', 'C10', '8', '098344');

insert into ticket
values('11037', NULL, NULL, '12.75', '0', '9:20', '1/23/2016', 'C11', '8', '098344');

insert into ticket
values('11038', NULL, NULL, '12.75', '0', '9:20', '1/23/2016', 'C11', '9', '120002');

insert into ticket
values('11039', NULL, NULL, '10.00', '0', '10:17', '1/23/2016','C11', '9', '120002');

insert into ticket
values('11040', NULL, NULL, '10.00', '0', '17:02', '2/02/2016','C11', '9', '540222');

insert into ticket
values('11041', NULL, NULL, '10.00', '0', '3:43', '4/12/2016','C11', '9', '760232');

insert into ticket
values('11042', NULL, NULL, '10.00', '0', '12:32', '4/12/2016','C11', '9', '760232');

insert into ticket
values('11043', NULL, NULL, '10.00', '0', '22:30', '1/03/2016','C11', '9', '943322');


--online_sold

insert into online_sold
values('21123','322222212378');

insert into online_sold
values('29470','009823784276');

insert into online_sold
values('36766','476665789432');


--cashier_sold
insert into cashier_sold
values('23123','22345542');

insert into cashier_sold
values('11084','22234122');

insert into cashier_sold
values('23162','90988886');

insert into cashier_sold
values('01037','22234122');

insert into cashier_sold
values('37466','90988886');


--machine_sold
insert into machine_sold
values('11001','0119');

insert into machine_sold
values('11456','0119');

insert into machine_sold
values('11034','0002');

insert into machine_sold
values('01098','0012');

