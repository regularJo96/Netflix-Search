import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Client{
    
    public static ArrayList<Media> currentList = new ArrayList<Media>();
	public static ArrayList<Media> data = new ArrayList<Media>();

	public static void main(String[] args){
		// to hold the records with filters in place
		
		ArrayList<Filter> filterList = new ArrayList<Filter>();
		DataFileParser database = new DataFileParser();

		database.parseFile();
		// holds all records in database (gets it from the local ArrayList maintained within database)
		data = database.getAllData();
		// current list starts out with all data
		currentList = database.getAllData();


		boolean keepGoing = true;
		while(keepGoing){
			printMainMenu();
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			if(input.toUpperCase().equals("Q")){
				System.out.println("Goodbye :)");
				keepGoing=false;
			}
			else if(input.toUpperCase().equals("L")){
				int count=0;
				for(Media record : currentList){
					System.out.println("____________________________________________________________________________________________");
					System.out.print("\n" + record);
				}
			}
			else if(input.toUpperCase().equals("A")){
				addFilters(filterList);
			}
			else if(input.toUpperCase().equals("R")){
				removeFilters(filterList);
			}
			else{
				System.out.println("That's not a valid selection. Try Again?\n");			}
		}
	}

	public static void removeFilters(ArrayList<Filter> filterList){
		if(filterList.size()==0){
			printCurrentFilters(filterList);
		}
		else{
			Scanner scan = new Scanner(System.in);
			String input = "";
			boolean notDoneRemoving=true;
			while(notDoneRemoving){
				printCurrentFilters(filterList);
				System.out.print("\nEnter the filter number you wish to remove.\nIf you changed your mind, enter 'B/b'\n\n>>> ");
				input = scan.nextLine();
				if(input.toLowerCase().equals("b")){
					break;
				}
				try{
					int index = Integer.parseInt(input);
					filterList.remove(index-1);
					updateCurrentList(filterList);
				}
				catch(NumberFormatException e){
					System.out.println("That's not a number?");
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("That's not one of the numbers listed?");
				}
				if(filterList.size()==0){ //catches case where removing the last filter
					System.out.println("There are no more filters to remove.\nTaking you back to main menu.");
					break;
				}
			}
		}
	}

	public static void addFilters(ArrayList<Filter> filterList){

		printAddMenu();
		Scanner scan = new Scanner(System.in);
		String input = "";
		boolean backToMainMenu=false;

		while(backToMainMenu==false){

			input = scan.nextLine();
			// choice holds all words input by user, this way there is a way to check if 
			// user forgot to actually add the parameter(s) to search for
			String[] choice = input.split(" ");
			input = choice[0];

			// get rest of input and assign to furtherInput
			String furtherInput = getFurtherInput(choice);
			//title, director, cast, country, rating, listed in and description use
			switch(input.toLowerCase()){
				case "movie":
					//start process of adding a movie filter
					addMovieFilter(input.toLowerCase(),filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "tv-show":
					//add series filter
					addSeriesFilter("series",filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "series":
					//add series filter
					addSeriesFilter("series",filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "tv-show/series":
					//add series filter
					addSeriesFilter("series",filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "title":
					//add title filter
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "director":
					//add director filter
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "cast":
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "country":
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "rating":
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "genre":
					if(choice.length>1){
						addGenericFilter(input.toLowerCase(),furtherInput,filterList);
					}
					else{
						getMissingParameters(input.toLowerCase(),filterList);
					}
					
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "runtime":
					addRuntimeFilter(input.toLowerCase(),filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "year":
					addYearFilter(input.toLowerCase(),filterList);
					System.out.print("\npeek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;

				case "f":
					printFilterTypes();
					break;
				case "b":
					backToMainMenu=true;
					break;
				case "l":
					printCurrentFilters(filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					break;
				default:
					// create a generic text filter that searches for a string of text within all fields
					addGenericTextFilter(input.toLowerCase(),filterList);
					System.out.print("peek of filters: " + filterList);
					// update the currentList of media pbjects with the added filters
					updateCurrentList(filterList);
					printAddMenu();
					
			}
		}
	}

	public static void updateCurrentList(ArrayList<Filter> filterList){
		// figure out how to place what items in current list
		// depending on what is in filterList
		ArrayList<Media> newCurrent = new ArrayList<Media>();
		boolean addToList=true;
		
		for(Media record : data){
			for(Filter filter : filterList){
				addToList=true;
				if(filter.matches(record)==false){
					addToList = false;
					break;
				}
			}
			if(addToList){
				newCurrent.add(record);
			}
		}
		currentList = copy(newCurrent);
	}

	public static String getFurtherInput(String[] array){
		String newString = "";
		 // "if array == {"year", ">", "1990"}" i starts at index 1
		for(int i=1;i<array.length;i++){
			for(int j=0;j<array[i].length();j++){
				newString=newString+array[i].charAt(j);
			}
			if(i!=array.length-1){
				newString=newString+" ";
			}
		}
		return newString;
	}

	public static ArrayList<Media> copy(ArrayList<Media> list){
		ArrayList<Media> newList = new ArrayList<Media>();
		for(Media record : list){
			newList.add(record);
		}
		return newList;
	}

	public static void printCurrentFilters(ArrayList<Filter> filterList){
		int count = 1;
		if(filterList.size()==0){
			System.out.print("\nThere are no filters added.\n");
		}
		else{
			System.out.println("Current Filters");
			System.out.println("****************************");
			for(Filter filter : filterList){
				System.out.print("\n" + "(" + count + ")  " + filter + "\n");
				System.out.println("****************************");
				count=count+1;
			}
		}
	}

	public static void printAddMenu(){
		System.out.print("\nADD FILTER MENU\n" + 
						   "Enter F/f to see the filter types.\n" + 
						   "Enter L/l to see current filters.\n" +
						   "Enter B/b to go back to the Main Menu\n\n" +
						   ">>> ");
	}

	public static void printFilterTypes(){
		System.out.print("\nFILTER TYPES:\n" + "1. 'movie'\t2. 'Tv Show/series'\n3. 'title'\t4. 'director'\n5. 'cast'\t"+
						   "6. 'country'\n7. 'rating'\t8. 'genre'\n9. 'year'\t10. 'runtime'\n\n"+ "Generic text will add a 'text' filter\n"+
						   "If a generic filter is added, it will be singified by the prefix 'All' in the filter list\n\n" +
						   "Enter the exact text to add the filter i.e 'movie', 'series', etc\n" +
						   "(Upon entering, you may be asked to type in further information.)\n\n"+
						   ">>> ");
	}

	public static void printMainMenu(){
		System.out.print("\nMAIN MENU\nEnter Q/q to quit\n" +
						   "Enter A/a to add a filter\n" +
						   "Enter R/r to remove a filter\n" + 
						   "Enter L/l to list the results ("+ hitCount()+")\n\n" +
						   ">>> ");
	}

	public static int hitCount(){
		int count=0;
		for(Media record : currentList){
			count=count+1;
		}
		return count;
	}

	public static void addGenericTextFilter(String input, ArrayList<Filter> filterList){
		System.out.println("Adding '" + input + "' filter to list");
		Filter filter = new Filter(input);
		filterList.add(filter);
	}
	// will work for title, director, cast, country, rating, listed in and description
	public static void getMissingParameters(String input, ArrayList<Filter> filterList){
		String furtherInput="";
		Scanner scan = new Scanner(System.in);
		System.out.print("Oops, it looks like you forgot to include the parameters you want to search for. Include them now.\n"
						  + ">>> " + input + " ");
		furtherInput=scan.nextLine();

		filterList.add(new Filter(input,furtherInput));
	}

	// overloaded takes in "further input" already
	// will work for title, director, cast, country, rating, listed in and description
	public static void addGenericFilter(String input, String furtherInput, ArrayList<Filter> filterList){
		filterList.add(new Filter(input,furtherInput));
	}
	
	public static void addMovieFilter(String input, ArrayList<Filter> filterList){
		boolean duplicate = false;
		for(Filter filter : filterList){
			if(filter.getFieldName().toLowerCase() == "movie"){
				duplicate=true;
			}
		}

		if(duplicate){
			System.out.println("\nYou have already added that filter.");
		}
		else{
			filterList.add(new Filter(input));
		}
	}

	//add series filter
	public static void addSeriesFilter(String input, ArrayList<Filter> filterList){	
		boolean duplicate = false;
		for(Filter filter : filterList){
			if(filter.getFieldName().toLowerCase() == "tv show"){
				duplicate=true;
			}
		}

		if(duplicate){
			System.out.println("\nYou have already added that filter.");
		}
		else{
			filterList.add(new Filter(input));
		}
	}

	public static void addRuntimeFilter(String input, ArrayList<Filter> filterList){
		boolean backToAddFilterMenu=false;
		String furtherInput="";
		while(backToAddFilterMenu==false){
			addRuntimeMenu();
		
			Scanner scan = new Scanner(System.in);
			boolean validRuntimeInput=false;

			while(validRuntimeInput==false){
				furtherInput="";
				furtherInput = scan.nextLine();
				//System.out.println(furtherInput);
				switch(furtherInput){
					case "1":
						furtherInput=">";
						input = "runtime" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "2":
						furtherInput="<";
						input = "runtime" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "3":
						furtherInput=">=";
						input = "runtime" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "4":
						furtherInput="<=";
						input = "runtime" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "5": 
						furtherInput="=";
						input = "runtime" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
				}
				input = "runtime" + " "+ furtherInput;
				//input validation
				validRuntimeInput = validateRuntimeInput(input);
			}
			
			System.out.print("\nAdded filter: " + input + "\nIf you wish to add another runtime filter, enter 'Y/y'." + 
							 "\nIf you wish to go back to the add filters menu, press 'B/b'.\n");

			filterList.add(new Filter(input));

			while(true){
				System.out.print("\n\n>>> ");
				String choice = scan.nextLine();
				if(choice.toLowerCase().equals("b")){
					backToAddFilterMenu=true;
					System.out.println("Taking you back to the ADD FILTER MENU");
					break;
				}
				else if(choice.toLowerCase().equals("y")){
					backToAddFilterMenu=false;
					break;
				}
				else{
					System.out.print("\nThat wasn't an option. Are you daft?\nAdd a new runtime filter 'Y/y'\nBack to filter menu 'B/b'");
				}
			}	
		}
	}

	public static void addYearFilter(String input, ArrayList<Filter> filterList){
		boolean backToAddFilterMenu=false;
		String furtherInput="";
		while(backToAddFilterMenu==false){
			addYearMenu();

			Scanner scan = new Scanner(System.in);
			boolean validYearInput=false;

			while(validYearInput==false){
				furtherInput="";
				furtherInput = scan.nextLine();
				while(furtherInputValid(furtherInput)==false){
					System.out.println("That's invalid.");
					addYearMenu();
					furtherInput = scan.nextLine();
				}
				//System.out.println(furtherInput);
				switch(furtherInput){
					case "1":
						furtherInput=">";
						input = "year" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "2":
						furtherInput="<";
						input = "year" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "3":
						furtherInput=">=";
						input = "year" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "4":
						furtherInput="<=";
						input = "year" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
					case "5": 
						furtherInput="=";
						input = "year" + " "+ furtherInput;
						System.out.print(">>> " + input + " ");
						furtherInput=furtherInput+" "+scan.nextLine();
						break;
				}
				input = "year" + " "+ furtherInput;
				//input validation
				validYearInput = validateYearInput(input);
				
			}
			
			System.out.print("\nAdded filter: " + input + "\nIf you wish to add another year filter, enter 'Y/y'." + 
							 "\nIf you wish to go back to the add filters menu, press 'B/b'.\n");

			filterList.add(new Filter(input));

			while(true){
				System.out.print("\n\n>>> ");
				String choice = scan.nextLine();
				if(choice.toLowerCase().equals("b")){
					backToAddFilterMenu=true;
					System.out.println("Taking you back to the ADD FILTER MENU");
					break;
				}
				else if(choice.toLowerCase().equals("y")){
					backToAddFilterMenu=false;
					break;
				}
				else{
					System.out.print("\nThat wasn't an option. Are you daft?\nAdd a new year filter 'Y/y'\nBack to filter menu 'B/b'");
				}
			}
		}
	}

	//returns true if input valid
	public static boolean furtherInputValid(String input){
		String[] furtherInput = input.split(" ");
		if(furtherInput.length<2){
			return false;
		}
		return true;
	}

	public static boolean validateRuntimeInput(String input){
		String[] string = input.split(" ");
		String runtimeString = string[2];
		int runtime=0;

		try{
			runtime = Integer.parseInt(runtimeString);
		}
		catch(NumberFormatException e){
			return false;
		}

		// checking if the runtime is not a negative number
		if (runtime < 0){
			return false;
		}
		
		return true;

	}

	public static boolean validateYearInput(String input){
		
		String[] string = input.split(" ");
		//check if the last element is a 4 digit year
		if(checkIfYear(string[2])==false){
			System.out.println("That is not a valid input.");
			addYearMenu();
			return false;
		}
		String key = removeLastWord(string);
		
		boolean valid=true;
		
		switch(key.toLowerCase()){
				case "year >":
					break;
				case "year <":
					break;
				case "year >=":
					break;
				case "year <=":
					break;
				case "year =":
					break;
				default:
					System.out.println("That is not a valid input.");
					addYearMenu();
					valid=false;
			}
		return valid;
	}

	public static void addYearMenu(){
		System.out.print("\nADDING YEAR FILTER\nthere are a few filter types to choose from.\n1.  > yyyy\n2.  < yyyy\n"+
							"3. >= yyyy\n4. <= yyyy\n5.  = yyyy\n\n>>> "+"year ");
	}

	public static void addRuntimeMenu(){
		System.out.print("\nADDING RUNTIME FILTER\nthere are a few filter types to choose from.\n(rrrr = runtime you wish, in minutes)\n1.  > rrrr\n2.  < rrrr\n"+
							"3. >= rrrr\n4. <= rrrr\n5.  = rrrr\n\n>>> "+"runtime ");
	}
	// return the input as a new phrase without the year
	public static String removeLastWord(String[] array){
		String newPhrase="";

		for(int i=0;i<array.length-1;++i){
			newPhrase=newPhrase+array[i] + " ";
		}
		// return the new phrase without the trailing whitespace
		return newPhrase.substring(0,newPhrase.length()-1);
	}

	public static boolean checkIfYear(String string){
		try{// if the string doesnt convert to an Integer, throws a number format exception
			int year = Integer.parseInt(string);
		}
		catch(NumberFormatException e){
			return false;
		}

		// if it gets here, it's at least a number
		int i=1;
		for(;i<string.length();++i); //increments i len of string times

		return i==4;
	}
}