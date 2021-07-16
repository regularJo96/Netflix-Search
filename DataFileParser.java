import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class DataFileParser{

	private static ArrayList<Media> media = new ArrayList<Media>();
	private static ArrayList<Media> movies = new ArrayList<Media>();
	private static ArrayList<Media> series = new ArrayList<Media>();

	public static ArrayList<Media> getAllData(){
		return media;
	}

	public static ArrayList<Media> getAllMovies(){
		return movies;
	}

	public static ArrayList<Media> getAllSeries(){
		return series;
	}

	public void parseFile(){
		String line = "";
		int count = 0;

		try{
        	String file = "netflix_titles.csv";
        	Scanner reader = new Scanner(new FileReader(file));
        	reader.nextLine();

			while(reader.hasNextLine()){
				place(reader.nextLine());
			}
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	public static void place(String line){

		ArrayList<String> lineToSplit = new ArrayList<String>();
		breakApart(lineToSplit, line);

		String type = lineToSplit.get(1);

		type = type.toUpperCase();
		if(type.equals("MOVIE")){
			//in order for readability, I will assign each elemnt in the
			// lineToSplit ArrayList to a variable to pass as arguments into
			// the constructor
			String id = lineToSplit.get(0);
			type = "Movie";
			String title = lineToSplit.get(2);
			String director = lineToSplit.get(3);
			String cast = lineToSplit.get(4);
			String country = lineToSplit.get(5);
			String dateAdded = lineToSplit.get(6);
			Integer releaseYear = Integer.parseInt(lineToSplit.get(7));
			String rating = lineToSplit.get(8);
			String duration = lineToSplit.get(9);
			String listedIn = lineToSplit.get(10);
			String description = lineToSplit.get(11);

			// because of the nature of the Media abstract class' constructor
			// some of the arguments unfortunately have to be out of order.

			Media movie = new Movie(id,type,title,director,cast,country,dateAdded,releaseYear,
									rating,listedIn,description,duration);

			//add it to list
			addToMediaList(movie);
			addToMovieList(movie);
		}
		else{
			//in order for readability, I will assign each elemnt in the
			// lineToSplit ArrayList to a variable to pass as arguments into
			// the constructor
			String id = lineToSplit.get(0);
			type = "TV Show";
			String title = lineToSplit.get(2);
			String director = lineToSplit.get(3);
			String cast = lineToSplit.get(4);
			String country = lineToSplit.get(5);
			String dateAdded = lineToSplit.get(6);
			Integer releaseYear = Integer.parseInt(lineToSplit.get(7));
			String rating = lineToSplit.get(8);
			String duration = lineToSplit.get(9);
			String listedIn = lineToSplit.get(10);
			String description = lineToSplit.get(11);
			Media series = new Series(id,type,title,director,cast,country,dateAdded,releaseYear,
									  rating,listedIn,description,duration);

			//add to list		
			addToMediaList(series);
			addToSeriesList(series);

		}

		// for(String item : lineToSplit){
		// 	System.out.println(item);
		// }
		//System.out.println("\n");

	}

	// break the line up into usable data Strings to add to lineSlit[]
	// to proper indexes to create media objects
	public static void breakApart(ArrayList<String> lineToSplit, String line){
		addFieldsToList(lineToSplit, line); // this function starts a cascade of all the other functions
	}

	// my thought process is to work from left to right down the line from the file given,
	// passing a shorter and shorter substring into the recursive functions
	// until finished parsing the whole line.
	public static void addFieldsToList(ArrayList<String> lineToSplit, String line){
		
		int index = 0;
		char charToCheck = line.charAt(index);
		String remainingFields = line;

		while(remainingFields.length()>0){
	
			if(charToCheck == 34){
				remainingFields = addFieldWithQuote(lineToSplit, remainingFields);

				if(remainingFields.length() != 0){
	
					charToCheck = remainingFields.charAt(index);
				}
			} 

			else{
				remainingFields = addFieldWithoutQuote(lineToSplit, remainingFields);

				if(remainingFields.equals("0")){
					System.out.print(lineToSplit.get(0));
				}
				if(remainingFields.length() != 0){
					charToCheck = remainingFields.charAt(index);
				}
			}
			
		}
	}

	public static String addFieldWithQuote(ArrayList<String> lineToSplit, String line){
	
		//start after the first quote, hence index = 1
		int index = 1;
		char charToCheck = line.charAt(index);
		String field = "";
		int quoteCount=1;
		boolean notEndQuote = true;

		while(notEndQuote){
			
			if(charToCheck == 34){
				quoteCount=quoteCount+1;

				if(quoteCount%2==0){
					
					notEndQuote = hasQuoteAfter(line.substring(index));
				}
				else{
					//if it's an odd number quote that means
					// it is a quote within a field, and I want to
					// display that information n the field
					field = field + charToCheck;
				}

			}
			// takes care of the scenario where the last character
			// in the field is an end quote
			if(charToCheck!=34){
				field = field + charToCheck;
			}
			index=index+1;
			try{
				charToCheck = line.charAt(index);
			}
			catch(Exception e){

			}
		}
		if(field.length()==0){
				field = "[nil]";
		}

		lineToSplit.add(field);

		try{
			return line.substring(index+1);
		}
		catch(IndexOutOfBoundsException e){
			return "";
		}
	}


	public static String addFieldWithoutQuote(ArrayList<String> lineToSplit, String line){
		int index = 0;
		char charToCheck = line.charAt(index);
		String field = "";
		
		// if it's the last field in the record
		if(lineToSplit.size()==11){
			while(index<line.length()){
				field = field + charToCheck;
				index = index+1;
				
				try{
					charToCheck = line.charAt(index);
				}
				catch(Exception e){
					lineToSplit.add(field);
					return "";
				}
			}
		}
		else{
			
			while(charToCheck!=44){
				field = field + charToCheck;
				index = index+1;
				
				charToCheck = line.charAt(index);
			}

		}
		
		if(field.length()==0){
			field = "[nil]";
		}
		lineToSplit.add(field);
		return line.substring(index+1);
	}

	public static boolean hasQuoteAfter(String line){
		boolean hasQuoteAfter = false;
		try{
			if(line.charAt(1) == 34){
				hasQuoteAfter = true;
			}
		}
		catch(IndexOutOfBoundsException e){
			hasQuoteAfter=false;
		}
		return hasQuoteAfter;
	}

	public static boolean containsNumber(String string){
		// checks for a number within the string given
		for(int i=0;i<string.length();i++){
			if(string.charAt(i) > 47 && string.charAt(i) < 58){
				return true;
			}
		}
		return false;
	}
	
	public static void addToMediaList(Media obj){
		media.add(obj);
	}

	public static void addToMovieList(Media obj){
		movies.add(obj);
	}

	public static void addToSeriesList(Media obj){
		series.add(obj);
	}

}