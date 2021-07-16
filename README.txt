Author: Josiah Anderson
Language: Java version 15.0.2

Overview:
This is a program that parses a CSV file populated with all Netflix offerings as of January 2021. It adds all the records to an ArrayList<Media> object as either Movie objects or Series objects. The program searches through the ArrayList and grabs the results dependant on the filters added. It allows the user to add as many filters as they want, i.e. type(movie, series), title, cast, director, country (made in), (release) year, runtime(in minutes), (content) rating, genre, and finally a generic text search that searches for all occurrences of said text within any and all records. 

Just a disclaimer, the way the search is set up ("and-ed together"), if both a movie filter and a series filter were added, there would be 0 results because a record cannot be both a movie and a series.


Here are some demos to help you out when using it, I plan to make it more user friendly by adding a GUI soon. Adding a runtime or year filter is shown at the end.


DEMOs 
To add a filter, navigate to the "add filter menu" and type in the filter you wish to add, along with the criteria you wish. For instance, if you want to search for a sci-fi movie made in Mexico, you might do something like this: 


(This is a copy and paste from the actual program running, enter notation added for less ambiguity)

MAIN MENU
Enter Q/q to quit
Enter A/a to add a filter
Enter R/r to remove a filter
Enter L/l to list the results (7787)

>>> a [ enter ]

ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> f [ enter ]

FILTER TYPES:
1. 'movie'      2. 'Tv Show/series'
3. 'title'      4. 'director'
5. 'cast'       6. 'country'
7. 'rating'     8. 'genre'
9. 'year'       10. 'runtime'

Generic text will add a 'text' filter
If a generic filter is added, it will be singified by the prefix 'All' in the filter list

Enter the exact text to add the filter i.e 'movie', 'series', etc
(Upon entering, you may be asked to type in further information.)

>>> movie [ enter ]

ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> genre sci-fi [ enter ]

peek of filters: [movie, genre sci-fi]
ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> country mexico [ enter ]

peek of filters: [movie, genre sci-fi, country mexico]
ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> b [ enter ]

MAIN MENU
Enter Q/q to quit
Enter A/a to add a filter
Enter R/r to remove a filter
Enter L/l to list the results (4)

>>> l [ enter ]
____________________________________________________________________________________________

Id:                s3498

Type:              Movie

title:             La Leyenda del Diamante

Director:          Roberto Girault Facha

Cast:              Ana Layevska, Alex Sirvent, Paulina Goto, Fernando Estrada, Alejandro Oliva, David Villegas, Xavier Cervantes, Cuauht??moc Duque, Karyme Lozano, Rodrigo Trevi?±o

Country/countries: Mexico

Date Added:        June 28, 2019

Release Year       2017

Rating:            TV-MA

Duration:          87 min

Listed In:         Action & Adventure, International Movies, Sci-Fi & Fantasy

Description:       When a gun-toting rebel tries to rescue her kidnapped sister, she finds herself up against a widely feared desperado and a cursed guardian of treasure.

____________________________________________________________________________________________

Id:                s4750

Type:              Movie

title:             Pan's Labyrinth

Director:          Guillermo del Toro

Cast:              Ivana Baquero, Sergi L??pez, Maribel Verd?º, Doug Jones, Ariadna Gil, ??lex Angulo, Manolo Solo, C??sar Vea, Roger Casamajor, Sergi Lopez

Country/countries: Mexico, Spain

Date Added:        January 1, 2020

Release Year       2006

Rating:            R

Duration:          119 min

Listed In:         Dramas, International Movies, Sci-Fi & Fantasy

Description:       Young Ofelia meets a mythical faun who claims she is destined to become princess of the Underworld. But first she must carry out three perilous tasks.

____________________________________________________________________________________________

Id:                s6485

Type:              Movie

title:             The Incident

Director:          Isaac Ezban

Cast:              Ra?ºl M??ndez, Nailea Norvind, Hern?¡n Mendoza, Humberto Busto, Fernando ??lvarez Rebeil, Gabriel Santoyo, Paulina Montemayor, H??ctor Mendoza, Leonel Tinajero, Marcos Moreno

Country/countries: Mexico

Date Added:        June 19, 2017

Release Year       2014

Rating:            TV-MA

Duration:          100 min

Listed In:         International Movies, Sci-Fi & Fantasy, Thrillers

Description:       Two criminals race down a stairwell, a cop close behind. A bickering family begins a road trip. Their journeys will take far longer than expected.

____________________________________________________________________________________________

Id:                s7163

Type:              Movie

title:             Total Recall

Director:          Paul Verhoeven

Cast:              Arnold Schwarzenegger, Rachel Ticotin, Sharon Stone, Michael Ironside, Ronny Cox, Marshall Bell, Michael Champion, Mel Johnson Jr., Roy Brocksmith, Rosemary Dunsmore

Country/countries: United States, Mexico

Date Added:        July 1, 2020

Release Year       1990

Rating:            R

Duration:          114 min

Listed In:         Action & Adventure, Sci-Fi & Fantasy

Description:       After getting a memory implant, working stiff Douglas Quaid discovers he might actually be a secret agent embroiled in a violent insurrection on Mars.



+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


TO ADD A YEAR or RUNTIME FILTER IT IS A LITTLE MORE COMPLICATED, but here is how to add a filter to search for all records with release year greater than 2001.

navigate to [add filter menu]
ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> year

ADDING YEAR FILTER
there are a few filter types to choose from.
1.  > yyyy
2.  < yyyy
3. >= yyyy
4. <= yyyy
5.  = yyyy

>>> year > 2001


Similarly, you can add a runtime filter in the same way:
ADD FILTER MENU
Enter F/f to see the filter types.
Enter L/l to see current filters.
Enter B/b to go back to the Main Menu

>>> runtime

ADDING RUNTIME FILTER
there are a few filter types to choose from.
(rrrr = runtime you wish, in minutes)
1.  > rrrr
2.  < rrrr
3. >= rrrr
4. <= rrrr
5.  = rrrr

>>> runtime > 120

