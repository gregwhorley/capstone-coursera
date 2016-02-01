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
        //String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //String bigMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        String bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //String bigRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        String bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";

        //SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);
        SecondRatings secondRatings = new SecondRatings(bigMovieCsv, bigRatingsCsv);

        System.out.println("Number of movies loaded from file: " + secondRatings.getMovieSize());
        System.out.println("Number of raters loaded from file: " + secondRatings.getRaterSize());

        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        //String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        String bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        String bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        //String bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_file_test.csv";

        //SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);
        SecondRatings secondRatings = new SecondRatings(bigMovieCsv, bigRatingsCsv);

        String movieId = secondRatings.getID("Vacation");
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(1);
        for (Rating rating : ratingArrayList) {
            if (rating.getItem().contains(movieId)) {
                System.out.println(secondRatings.getTitle(movieId) + " has average rating: " + rating.getValue());
            }
        }
    }
}
