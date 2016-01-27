package coursera.capstone.project;

/**
 * Created by greg on 1/27/16.
 */
public class MovieRunnerAverage {

    public MovieRunnerAverage() {

    }

    public void printAverageRatings() {
        /*
        This method should:
        - Create a SecondRatings object and use the CSV filenames of movie information
             and ratings information from the first assignment when calling the constructor.
        - Print the number of movies and number of raters from the two files by calling
             the appropriate methods in the SecondRatings class. Test your program to make
             sure it is reading in all the data from the two files. For example, if you run
             your program on the files ratings_short.csv and ratedmovies_short.csv, you
             should see 5 raters and 5 movies.
         */
        String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";

        //Calling SecondRatings constructor without parameters will use the bigger CSV files
        SecondRatings secondRatings = new SecondRatings(shortMovieCsv, shortRatingsCsv);

        System.out.println("Number of movies loaded from file: " + secondRatings.getMovieSize());
        System.out.println("Number of raters loaded from file: " + secondRatings.getRaterSize());
    }
}
