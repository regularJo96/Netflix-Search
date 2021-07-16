// initially, I thought that the director field was always blank in the TV Show fields,
// but that is not the case.


public abstract class Media{
	protected String id;
	protected String type;
	protected String director;
	protected String title;
	protected String cast;
	protected String country;
	protected String dateAdded;
	protected Integer releaseYear;
	protected String rating;
	protected String listedIn;
	protected String description;

	public Media(String id, String type, String title,String director,String cast,String country, 
				 String dateAdded, Integer releaseYear, String rating,
				 String listedIn, String description){

		this.id=id;
		this.type=type;
		this.director=director;
		this.title=title;
		this.cast=cast;
		this.country=country;
		this.dateAdded=dateAdded;
		this.releaseYear=releaseYear;
		this.rating=rating;
		this.listedIn=listedIn;
		this.description=description;

	}

	public abstract String getId();

	public abstract String getType();

	public abstract String getDirector();

	public abstract String getTitle();

	public abstract String getCast();
	
	public abstract String getCountry();
	
	public abstract String getDateAdded();
	
	public abstract Integer getReleaseYear();
	
	public abstract String getRating();
	
	public abstract String getListedIn();
	
	public abstract String getDescription();


}