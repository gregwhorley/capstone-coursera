package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by greg on 2/15/2016.
 */
public class MovieRunnerSimilarRatings {
    private String shortMovieCsv, shortRatingsCsv, bigMovieCsv, bigRatingsCsv;
    FourthRatings fourthRatings;
    ArrayList<Rating> ratingArrayList;

    public MovieRunnerSimilarRatings() {
        //shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        bigMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        bigRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        //bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";

        //MovieDatabase.initialize(shortMovieCsv);
        MovieDatabase.initialize(bigMovieCsv);
        RaterDatabase.initialize(bigRatingsCsv);
        fourthRatings = new FourthRatings();
    }


    public void printAverageRatings(int minimalRaters) {
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + RaterDatabase.size());

        ratingArrayList = fourthRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + RaterDatabase.size());
        AllFilters filterList = new AllFilters();
        filterList.addFilter(new YearAfterFilter(year));
        filterList.addFilter(new GenreFilter(genre));
        ratingArrayList = fourthRatings.getAverageRatingsByFilter(minimalRaters, filterList);

        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total movies: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) +
                    " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));

        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
        int minimalRaters = 30;
        int year = 1990;
        String genre = "Drama";
        int minMinutes = 90;
        int maxMinutes = 180;
        String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        //movieRunnerSimilarRatings.printAverageRatings(minimalRaters);
        movieRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre(minimalRaters, year, genre);
    }
}
