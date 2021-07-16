public class Filter<E>{ 
	boolean specialField=false; 
	Integer field; // used to index the database arraylist based on field number
	String fieldName; // holds the name of the field, "director, title, etc"
	String targetString; // holds target string obv
	Integer targetInt; // holds target year or runtime
	String descriptor; // holds < > = for use w/ year and duration


	// constructor for year and runtime and generic text
	public Filter(String input){
		String[] newString = input.split(" ");

		if(newString[0].equals("runtime") || newString[0].equals("year")){
			this.fieldName=newString[0];
			if(newString[0].equals("runtime")){
				this.field=9;
			}
			else{
				this.field=7;
			}
			
			this.descriptor=newString[1];
			targetString = newString[2];
			this.targetInt = Integer.parseInt(targetString);
		}
		else if(newString[0].equals("movie")){
			this.field=1;
			this.fieldName="movie";
		}
		else if(newString[0].equals("series")){
			this.field=1;
			this.fieldName="tv show";
		}
		else{
			this.specialField = true;
			this.targetString = input;
			this.fieldName="All";
		}
	}

	//constructor for generic field filter
	public Filter(String input, String furtherInput){
		// setting field name 
		this.fieldName=input;
		// setting field
		switch(input){
			case "title":
				this.field=2; //index within ArrayList
				break;
			case "director":
				this.field=3;
				break;
			case "cast":
				this.field=4;
				break;
			case "country":
				this.field=5;
				break;
			case "rating":
				this.field=8;
				break;
			case "genre":
				this.field=10;
				break;
		}
		
		this.targetString = furtherInput;
	}

	public boolean matches(Media record){
		if(this.fieldName.equals("All")){
			boolean isIn=false;
			if (record instanceof Movie){
				return record.getTitle().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getDirector().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getCast().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getCountry().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getRating().toLowerCase().equals(this.targetString.toLowerCase()) ||
				   record.getListedIn().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getReleaseYear().equals(this.targetInt) ||
				   ((Movie)record).getDuration().equals(this.targetInt) ||
				   record.getDescription().contains(this.targetString.toLowerCase());
			}
			else{
				return record.getTitle().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getDirector().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getCast().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getCountry().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getRating().toLowerCase().equals(this.targetString.toLowerCase()) ||
				   record.getListedIn().toLowerCase().contains(this.targetString.toLowerCase()) ||
				   record.getReleaseYear().equals(this.targetInt) ||
				   ((Series)record).getDuration().toLowerCase().contains(this.targetString) ||
				   record.getDescription().contains(this.targetString.toLowerCase());
			}


		}
		else{
			switch(this.field){
				case 1: //type

					return record.getType().toLowerCase().equals(this.fieldName);
					
				case 2: //title
					return record.getTitle().toLowerCase().contains(this.targetString.toLowerCase());
					
				case 3: // director
					return record.getDirector().toLowerCase().contains(this.targetString.toLowerCase());
					
				case 4: // cast
					return record.getCast().toLowerCase().contains(this.targetString.toLowerCase());
					
				case 5: // country
					return record.getCountry().toLowerCase().contains(this.targetString.toLowerCase());
					
				case 7: // release_date

					if (record instanceof Movie){
						
						if(this.descriptor.equals("=")){
							return ((Movie)record).getReleaseYear().equals(this.targetInt);
						}
						else if(this.descriptor.equals(">")){
							if(((Movie)record).getReleaseYear().compareTo(this.targetInt)>0){
								return true;
							}
						}
						else if(this.descriptor.equals("<")){
							if(((Movie)record).getReleaseYear().compareTo(this.targetInt)<0){
								return true;
							}
						}
						else if(this.descriptor.equals(">=")){
							if(((Movie)record).getReleaseYear().compareTo(this.targetInt)>0 || ((Movie)record).getReleaseYear().equals(this.targetInt)){
								return true;
							}
						}
						else if (this.descriptor.equals("<=")){
							if(((Movie)record).getReleaseYear().compareTo(this.targetInt)<0 || ((Movie)record).getReleaseYear().equals(this.targetInt)){
								return true;
							}
						}
					}
					else{
						if(this.descriptor.equals("=")){
							return ((Series)record).getReleaseYear().equals(this.targetInt);
						}
						else if(this.descriptor.equals(">")){
							if(((Series)record).getReleaseYear().compareTo(this.targetInt)>0){
								return true;
							}
						}
						else if(this.descriptor.equals("<")){
							if(((Series)record).getReleaseYear().compareTo(this.targetInt)<0){
								return true;
							}
						}
						else if(this.descriptor.equals(">=")){
							if(((Series)record).getReleaseYear().compareTo(this.targetInt)>0 || ((Series)record).getReleaseYear().equals(this.targetInt)){
								return true;
							}
						}
						else if (this.descriptor.equals("<=")){
							if(((Series)record).getReleaseYear().compareTo(this.targetInt)<0 || ((Series)record).getReleaseYear().equals(this.targetInt)){
								return true;
							}
						}
					}
					
					return false;
				
				case 8: // rating
					return record.getRating().toLowerCase().equals(this.targetString.toLowerCase());
					
				case 9: // duration

					if (record instanceof Movie){

						if(this.descriptor.equals("=")){
							return ((Movie)record).getDuration().equals(this.targetInt);
						}
						else if(this.descriptor.equals(">")){
							if(((Movie)record).getDuration().compareTo(this.targetInt)>0){
								return true;
							}
						}
						else if(this.descriptor.equals("<")){
							if(((Movie)record).getDuration().compareTo(this.targetInt)<0){
								return true;
							}
						}
						else if(this.descriptor.equals(">=")){
							if(((Movie)record).getDuration().compareTo(this.targetInt)>0 || ((Movie)record).getDuration().equals(this.targetInt)){
								return true;
							}
						}
						else if (this.descriptor.equals("<=")){
							if(((Movie)record).getDuration().compareTo(this.targetInt)<0 || ((Movie)record).getDuration().equals(this.targetInt)){
								return true;
							}
						}
					}
					
					return false;
					 
					
				case 10: // genre
					return record.getListedIn().toLowerCase().contains(this.targetString.toLowerCase());

			}
		}
		
		
		return false;
	}

	public Integer getField(){
		return this.field;
	}

	//use for year and 
	public Integer getTargetInt(){
		return this.targetInt;
	}

	public String getFieldName(){
		return this.fieldName;
	}

	public String getTargetString(){
		return this.targetString;
	}
	public String toString(){

		if(this.specialField){
			return String.format("all '%s'", this.targetString);
		}
		else if(this.fieldName.toLowerCase().equals("movie")){
			return this.fieldName;
		}
		else if(this.fieldName.toLowerCase().equals("tv show")){
			return this.fieldName;
		}
		else if(this.fieldName.toLowerCase().equals("year")){
			return this.fieldName + " " + this.descriptor + " " + this.targetString;
		}
		else if(this.fieldName.toLowerCase().equals("runtime")){
			return this.fieldName + " " + this.descriptor + " " + this.targetString + " minutes";
		}
		else{
			return this.fieldName + " " + this.targetString;
		}
		
	}
}