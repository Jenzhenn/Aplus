package core;

public class Movie {
	private String genre;
	private String language;
	private int length;
	private String movieID;
	private String rating;
	
	public Movie(String genre, String language, int length, String movieID, String rating){
		super();
		this.genre = genre;
		this.language = language;
		this.length = length;
		this.movieID = movieID;
		this.rating = rating;
	}
	public String printMovie(){
		return "MID: "+movieID+"\nGenre: "+genre+"\nLength: "+String.valueOf(length)+"\nLanguage: "+language+"\nRating: "+rating;
	}
}

/*
genre varchar(10) null,
language varchar(10)  null,
length int not null,
movie_ID char(6) not null,
rating char(4) null,
primary key(movie_ID)
*/