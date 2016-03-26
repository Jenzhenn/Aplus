package core;

public class Ticket {
	private String ticketNum;
	
	private String cName;
	private String cPhone;
	private float price;
	private int isSold;
	private String showTime;
	private String showDate;
	private String seatNum;
	private int audiNum;
	private String movieID;
	private String title;

}

/*
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
	
	*/
