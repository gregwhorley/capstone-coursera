package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by greg on 2/8/2016.
 */
public class MovieRunnerWithFilters {
    private String shortMovieCsv, shortRatingsCsv, bigMovieCsv, bigRatingsCsv;
    ThirdRatings thirdRatings;
    ArrayList<Rating> ratingArrayList;

    public MovieRunnerWithFilters() {
        //shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //bigMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //bigRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        //bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        MovieDatabase.initialize(shortMovieCsv);
        thirdRatings = new ThirdRatings(shortRatingsCsv);
    }

    public void printAverageRatings(int minimalRaters) {
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + thirdRatings.getRaterSize());

        ratingArrayList = thirdRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear(int minimalRaters, int year) {
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + thirdRatings.getRaterSize());
        ratingArrayList = thirdRatings.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(year));
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + "raters and" +
                " was made on or after " + year + ": " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) +
                    " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre(int minimalRaters, String genre) {
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + thirdRatings.getRaterSize());
        ratingArrayList = thirdRatings.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters and " +
                "is of genre " + genre + ": " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
        int minimalRaters = 1;
        int year = 2000;
        String genre = "Crime";
        //movieRunnerWithFilters.printAverageRatings(minimalRaters);
        //movieRunnerWithFilters.printAverageRatingsByYear(minimalRaters,year);
        movieRunnerWithFilters.printAverageRatingsByGenre(minimalRaters, genre);
    }
}
