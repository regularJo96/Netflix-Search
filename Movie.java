public class Movie extends Media{

	private String duration;
	private static Integer count=0;

	public Movie(String id,String type,String title,String director,String cast,String country,
				 String dateAdded,Integer releaseYear,String rating,String listedIn,
				 String description,String duration){
		super(id, type, title, director, cast, country, dateAdded, 
			  releaseYear, rating, listedIn, description);

		this.duration = duration;
		count=count+1;
	}

	@Override
	public String toString(){
		return String.format("%-19s%s%n%n" + "%-19s%s%n%n" + "%-19s%s%n%n" + "%-19s%s%n%n" +
			   "%-19s%s%n%n" + "%-19s%s%n%n" +"%-19s%s%n%n" + "%-19s%d%n%n" + 
			   "%-19s%s%n%n" +"%-19s%s min%n%n" + "%-19s%s%n%n" + "%-19s%s%n%n",
			   "Id:",getId(),"Type:",getType(),"title:",getTitle(),
			   "Director:",getDirector(),"Cast:",getCast(),"Country/countries:",getCountry(),
			   "Date Added:",getDateAdded(),"Release Year",getReleaseYear(),"Rating:",getRating(),
			   "Duration:",getDuration(),"Listed In:",getListedIn(),
			   "Description:", getDescription());
	}
	public static Integer getCount(){
		return count;
	}

	public Integer getDuration(){
		// remove mins from the end of duration
		String min = "";
		int index=0;
		char charToCheck = this.duration.charAt(index);
		while(charToCheck!=32){//blank space ascii code is 32. The duration text is made up of ## min
			min = min + charToCheck;
			index = index + 1;
			charToCheck = this.duration.charAt(index);
		} 
		Integer intDuration = Integer.parseInt(min);
		return intDuration;
	}

	// START Media abstract methods
	public String getId(){
		return super.id;
	}

	public String getType(){
		return super.type;
	}
	public String getDirector(){
			return this.director;
	}

	public String getTitle(){
		return super.title;
	}

	public String getCast(){
		return super.cast;
	}
	
	public String getCountry(){
		return super.country;
	}
	
	public String getDateAdded(){
		return super.dateAdded;
	}
	
	public Integer getReleaseYear(){
		return super.releaseYear;
	}
	
	public String getRating(){
		return super.rating;
	}
	
	public String getListedIn(){
		return super.listedIn;
	}
	
	public String getDescription(){
		return super.description;
	}
	// END Media abstract methods

}