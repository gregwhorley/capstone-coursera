package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by greg on 1/27/16.
 */
public class MovieRunnerAverage {

    public MovieRunnerAverage() {

    }

    public void printAverageRatings(int minimalRaters) {
        String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //Calling SecondRatings constructor without parameters will use the bigger CSV files
        SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);

        System.out.println("Number of movies loaded from file: " + secondRatings.getMovieSize());
        System.out.println("Number of raters loaded from file: " + secondRatings.getRaterSize());

        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //Calling SecondRatings constructor without parameters will use the bigger CSV files
        SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);

        String movieId = secondRatings.getID("The Godfather");
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(1);
        for (Rating rating : ratingArrayList) {
            if (rating.getItem().contains(movieId)) {
                System.out.println(secondRatings.getTitle(movieId) + " has average rating: " + rating.getValue());
            }
        }
    }
}
