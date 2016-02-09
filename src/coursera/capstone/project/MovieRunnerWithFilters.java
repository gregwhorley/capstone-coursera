package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by greg on 2/8/2016.
 */
public class MovieRunnerWithFilters {

    public MovieRunnerWithFilters() {
    }

    public void printAverageRatings(int minimalRaters) {
        //String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //String bigMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //String bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //String bigRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        //String bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";

        ThirdRatings thirdRatings = new ThirdRatings(shortRatingsCsv);
        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize(shortMovieCsv);

        System.out.println("Number of movies loaded from file: " + movieDatabase.size());
        System.out.println("Number of raters loaded from file: " + thirdRatings.getRaterSize());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + movieDatabase.getTitle(rating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
        int minimalRaters = 1;
        movieRunnerWithFilters.printAverageRatings(minimalRaters);
    }
}
