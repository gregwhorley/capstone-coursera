package coursera.capstone.project;

/*
 * FirstRatings.java
 * @author Greg Whorley
 * @version 1.0
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;

public class FirstRatings {

    public FirstRatings() {
    }

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        CSVParser csvParser = getParserFromFile(filename);
        for (CSVRecord csvRecord: csvParser) {
            movieArrayList.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),
                    csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),
                    csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes"))));
        }
        return movieArrayList;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        /*
         * This method should process every record from the CSV file whose name is filename,
         * a file of raters and their ratings, and return an ArrayList of type Rater with
         * all the rater data from the file.
         */
        ArrayList<Rater> raterArrayList = new ArrayList<Rater>();
        CSVParser csvParser = getParserFromFile(filename);

        for (CSVRecord csvRecord : csvParser) {
            if (!raterArrayList.contains(csvRecord.get("rater_id"))) {
                Rater rater = new Rater(csvRecord.get("rater_id"));
                rater.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                raterArrayList.add(rater);
            } else if (raterArrayList.contains(csvRecord.get("rater_id"))) {
                int raterIndex = raterArrayList.indexOf(csvRecord.get("rater_id"));
                Rater rater = raterArrayList.get(raterIndex);
                rater.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                raterArrayList.set(raterIndex, rater);
            }
        }
        return raterArrayList;
    }

    private CSVParser getParserFromFile(String filename) {
        FileResource fileResource = new FileResource(filename);
        return fileResource.getCSVParser();
    }

    public static void main(String[] args) {

    }

}