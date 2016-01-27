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
        ArrayList<Rater> raterArrayList = new ArrayList<Rater>();
        CSVParser csvParser = getParserFromFile(filename);

        for (CSVRecord csvRecord : csvParser) {
            Rater raterFromCsvRecord = new Rater(csvRecord.get("rater_id"));
            int indexOfRater = findIndexOfRaterInArray(raterArrayList, raterFromCsvRecord);
            //if no Rater array entries contain the "rater_id"
            if (indexOfRater == -1) {
                //add rating in csvRecord to inner Rating array
                raterFromCsvRecord.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                //add new rater object to raterArrayList
                raterArrayList.add(raterFromCsvRecord);
            }
            //we reach this point if the "rater_id" was found in the Rater array
            else {
                //add current "movie_id" and "rating" to inner Rating array
                Rater existingRaterInArray = raterArrayList.get(indexOfRater);
                existingRaterInArray.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
            }
        }
        return raterArrayList;
    }

    private CSVParser getParserFromFile(String filename) {
        FileResource fileResource = new FileResource(filename);
        return fileResource.getCSVParser();
    }

    private int findIndexOfRaterInArray(ArrayList<Rater> raterList, Rater rater) {
        for (int index = 0; index < raterList.size(); index++) {
            Rater raterFromList = raterList.get(index);
            if (raterFromList.getID().contains(rater.getID())) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }

}