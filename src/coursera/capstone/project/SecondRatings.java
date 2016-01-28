package coursera.capstone.project;

/**
 * Used for calculating average ratings of movies
 *
 * @author Greg Whorley
 * @version 1.0
 */

import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv",
                "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        /*
        This method returns a double representing the average movie rating for this ID
        if there are at least minimalRaters ratings. If there are not minimalRaters
        ratings, then it returns 0.0.

        -Rater object has getItemsRated() method that returns an ArrayList<String> of movie IDs
        -Pull Rater objects out of myRaters
        -For each Rater object, search for movieId through hasRating()
        -When the target movieId is found, get its rating through getRating()
        -Once all Rater objects have been searched, enumerate the ratings
        -If the number of ratings for movieId is less than minimalRaters, return 0.0
        -If the number of ratings for movieId is minimalRaters or higher, calculate and return the average
         */
        double totalRatings = 0.0;
        int numberOfRatings = 0;
        for (Rater rater : myRaters) {
            if (rater.hasRating(movieId)) {
                numberOfRatings++;
                totalRatings += rater.getRating(movieId);
            }
        }
        if (numberOfRatings < minimalRaters) {
            return 0.0;
        }
        return totalRatings / (double) numberOfRatings;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        /*
        This method should find the average rating for every movie that has been rated by at least
        minimalRaters raters. Store each such rating in a Rating object in which the movie ID and
        the average rating are used in creating the Rating object. The method getAverageRatings
        should return an ArrayList of all the Rating objects for movies that have at least the
        minimal number of raters supplying a rating. For example, if minimalRaters has the value
        10, then this method returns rating information (the movie ID and its average rating) for
        each movie that has at least 10 ratings. You should consider calling the private getAverageByID method.
         */
        ArrayList<Rating> ratingArrayList = new ArrayList<Rating>();
        for (Rater rater : myRaters) {

        }

        return ratingArrayList;
    }

}