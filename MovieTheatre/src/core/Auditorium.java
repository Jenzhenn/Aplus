package core;

public class Auditorium {
	private int audi_num;
	private int avail_seat;
	
	public Auditorium(int audi_num, int avail_seat){
		this.audi_num = audi_num;
		this.avail_seat = avail_seat;
	}
	
	public Integer getAvailSeat(){
		return avail_seat;
	}
	
	public Integer getAudi(){
		return audi_num;
	}
	
	public String printSeat(){
		return "Auditorium: " + audi_num + " Available seats: " + avail_seat;
	}
}
