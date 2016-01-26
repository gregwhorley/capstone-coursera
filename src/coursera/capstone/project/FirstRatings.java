package coursera.capstone.project;

/*
 * FirstRatings.java
 * @author Greg Whorley
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public FirstRatings() {
    }

    public ArrayList<Movie> loadMovies(String filename) {
        /*
         *This method should process every record from the CSV file whose name is filename,
         * a file of movie information, and return an ArrayList of type Movie with all of
         * the movie data from the file.
         */
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        FileResource fileResource = new FileResource(filename);
        CSVParser csvParser = fileResource.getCSVParser();
        for (CSVRecord csvRecord: csvParser) {
            movieArrayList.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),
                    csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),
                    csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes"))));
        }
        return movieArrayList;
    }

    public static void main(String[] args) {

    }

}