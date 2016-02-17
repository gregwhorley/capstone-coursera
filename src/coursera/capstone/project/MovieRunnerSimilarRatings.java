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

    public void printSimilarRatings() {
        /*
        This method creates a new FourthRatings object, reads data into the MovieDatabase and RaterDatabase,
        and then calls getSimilarRatings for a particular rater ID, a number for the top number of similar
        raters, and a number of minimal rateSimilarRatings, and then lists recommended movies and their
        similarity ratings. For example, using the files ratedmoviesfull.csv and ratings.csv and the rater
        ID 65, the number of minimal raters 5, and the number of top similar raters set to 20, the movie
        returned with the top rated average is “The Fault in Our Stars”.
         */
    }

    public void printSimilarRatingsByGenre() {
        /*
        This method is similar to printSimilarRatings but also uses a genre filter and then lists recommended
        movies and their similarity ratings, and for each movie prints the movie and its similarity rating on
        one line and its genres on a separate line below it. For example, using the files ratedmoviesfull.csv
        and ratings.csv, the genre “Action”, the rater ID 65, the number of minimal raters set to 5, and the
        number of top similar raters set to 20, the movie returned with the top rated average is “Rush”.
         */
    }

    public void printSimilarRatingsByDirector() {
        /*
        This method is similar to printSimilarRatings but also uses a director filter and then lists recommended
        movies and their similarity ratings, and for each movie prints the movie and its similarity rating on one
        line and its directors on a separate line below it. For example, using the files ratedmoviesfull.csv and
        ratings.csv, the directors “Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone”, the rater ID
        1034, the number of minimal raters set to 3, and the number of top similar raters set to 10, the movie
        returned with the top rated average is “Unforgiven”.
         */
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        /*
        This method is similar to printSimilarRatings but also uses a genre filter and a minutes filter and
        then lists recommended movies and their similarity ratings, and for each movie prints the movie, its
        minutes, and its similarity rating on one line and its genres on a separate line below it. For example,
        using the files ratedmoviesfull.csv and ratings.csv, the genre “Adventure”, minutes between 100 and 200
        inclusive, the rater ID 65, the number of minimal raters set to 5, and the number of top similar raters
        set to 10, the movie returned with the top rated average is “Interstellar”.
         */
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        /*
        This method is similar to printSimilarRatings but also uses a year-after filter and a minutes filter
        and then lists recommended movies and their similarity ratings, and for each movie prints the movie,
        its year, its minutes, and its similarity rating on one line. For example, using the files
        ratedmoviesfull.csv and ratings.csv, the year 2000, minutes between 80 and 100 inclusive, the rater
        ID 65, the number of minimal raters set to 5, and the number of top similar raters set to 10, the movie
        returned with the top rated average is “The Grand Budapest Hotel”.
         */
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
