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
	private String confCode;
	
	public Ticket(String ticketNum,String cName,String cPhone,float price,int isSold,String showTime,String showDate,String seatNum,int audiNum,String movieID,String title, String confCode){
		super();
		this.ticketNum = ticketNum;
		this.cName = cName;
		this.cPhone = cPhone;
		this.price = price;
		this.setIsSold(isSold);
		this.showDate = showDate;
		this.showTime = showTime;
		this.seatNum = seatNum;
		this.setAudiNum(audiNum);
		this.movieID = movieID;
		this.title = title;
		this.confCode = confCode;
	}
	
	public String printTicket(){
		return "Ticket Number: "+ticketNum+
				"\nMovie: "+title+
				"\nMovieID: "+movieID+
				"\nPrice: "+price+
				"\nisSold?: "+getIsSold()+
				"\nCustomer Name: "+cName+
				"\nCustomer Phone: "+cPhone+
				"\nDate: "+showDate+
				"\nTime: "+showTime+
				"\nAuditorium: "+getAudiNum()+
				"\nSeat Number: "+seatNum;	
	}

	public int getIsSold() {
		return isSold;
	}

	public void setIsSold(int isSold) {
		this.isSold = isSold;
	}

	public int getAudiNum() {
		return audiNum;
	}

	public int setAudiNum(int audiNum) {
		this.audiNum = audiNum;
		return audiNum;
	}
	
	public String getConfCode(){
		return confCode;
	}
	
	public String getSeatNum(){
		return seatNum;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getShowTime(){
		return showTime;
	}
	public String getShowDate(){
		return showDate;
	}
 
}
