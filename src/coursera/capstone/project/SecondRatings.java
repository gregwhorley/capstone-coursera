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
        ArrayList<Rating> ratingArrayList = new ArrayList<Rating>();
        for (Rater rater : myRaters) {
            ArrayList<String> ratingList = rater.getItemsRated();
            for (int index = 0; index < ratingList.size(); index++) {
                double averageRating = getAverageByID(ratingList.get(index), minimalRaters);
                if (averageRating != 0.0) {
                    ratingArrayList.add(new Rating(ratingList.get(index), averageRating));
                }
            }
        }
        return ratingArrayList;
    }

}