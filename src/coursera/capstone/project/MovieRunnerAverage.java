package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by greg on 1/27/16.
 */
public class MovieRunnerAverage {

    public MovieRunnerAverage() {

    }

    public static void printAverageRatings() {
        //String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //Calling SecondRatings constructor without parameters will use the bigger CSV files
        SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);

        System.out.println("Number of movies loaded from file: " + secondRatings.getMovieSize());
        System.out.println("Number of raters loaded from file: " + secondRatings.getRaterSize());

        int minimalRaters = 3;
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        for (Rating rating : ratingArrayList) {
            System.out.println(secondRatings.getTitle(rating.getItem()) + " " + rating.getValue());
        }
    }
}
