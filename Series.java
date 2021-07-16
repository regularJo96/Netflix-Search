public class Series extends Media{

	private String duration;
	private static Integer count=0;

	public Series(String id,String type,String title,String director,String cast,String country,
				 String dateAdded,Integer releaseYear,String rating,String listedIn,
				 String description, String duration){
		super(id, type, title, director, cast, country, dateAdded, 
			  releaseYear, rating, listedIn, description);
		
		this.duration = duration;
		count=count+1;
	}

	@Override
	public String toString(){
		return String.format("%-19s%s%n%n" + "%-19s%s%n%n" + "%-19s%s%n%n" + "%-19s%s%n%n" +
			   "%-19s%s%n%n" + "%-19s%s%n%n" +"%-19s%s%n%n" + "%-19s%d%n%n" + 
			   "%-19s%s%n%n" +"%-19s%s %n%n" + "%-19s%s%n%n" + "%-19s%s%n%n",
			   "Id:",getId(),"Type:",getType(),"title:",getTitle(),
			   "Director:",getDirector(),"Cast:",getCast(),"Country/countries:",getCountry(),
			   "Date Added:",getDateAdded(),"Release Year",getReleaseYear(),"Rating:",getRating(),
			   "Duration:",getDuration(),"Listed In:",getListedIn(),
			   "Description:", getDescription());
	}

	public static Integer getCount(){
		return count;
	}

	public String getDuration(){
		return this.duration;
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
	
	// need to strip date added to return as Integer later
	// if I want to implement checking for length of series
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