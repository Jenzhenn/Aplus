package core;

import java.util.Set;


public class Movie {
	private String title;
	private String genre;
	private String language;
	private int length;
	private String movieID;
	private String rating;
	private Set<String> directorList;
	private Set<String> actorList;
	private double avgSold;
	
	public Movie(String title, String genre, String language, int length, String movieID, String rating, Set<String> directorList, Set<String> actorList){
		super();
		this.title = title;
		this.genre = genre;
		this.language = language;
		this.length = length;
		this.movieID = movieID;
		this.rating = rating;
		this.directorList = directorList;
		this.actorList = actorList;
	}
	
	public Movie(String MID, String title, double avgSold){
	    movieID = MID;
	    this.title = title;
	    this.avgSold = avgSold*100;
	}
	
	public double getAvgSold(){
	    return avgSold;
	}
	
	public String printMovie(){
		return "Title:" + title + "\nMID: "+movieID+"\nGenre: "+genre+"\nLength: "+String.valueOf(length)+"\nLanguage: "+language+"\nRating: "+rating + "\nDirector(s): " + directorList + "\nActor(s): " + actorList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieID == null) ? 0 : movieID.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (movieID == null) {
			if (other.movieID != null)
				return false;
		} else if (!movieID.equals(other.movieID))
			return false;
		return true;
	}
	
	public void mergeDirector(Movie sameMovie){
		directorList.addAll(sameMovie.getDirector());
	}
	
	public Set<String> getDirector(){
		return directorList;
	}
	
	public Set<String> getActor(){
		return actorList;
	}
	public void mergeActor(Movie sameMovie) {
		actorList.addAll(sameMovie.getActor());
		
	}
	public Object getTitle() {
		return title;
	}
	public Object getLanguage() {
		return language;
	}
	public Object getLength() {
		return length;
	}
	public Object getRating() {
		return rating;
	}
	public Object getMovieID() {
		return movieID;
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