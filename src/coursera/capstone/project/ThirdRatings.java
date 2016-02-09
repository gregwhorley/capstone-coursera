package coursera.capstone.project;

/**
 * Used for calculating average ratings of movies
 *
 * @author Greg Whorley
 * @version 1.0
 */

import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter());
        for (int index = 0; index < movieIds.size(); index++) {
            double averageRating = getAverageByID(movieIds.get(index), minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(movieIds.get(index), averageRating));
            }
        }
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter criteriaFilter) {
        /*
        This method should create and return an ArrayList of type Rating of all the movies that
        have at least minimalRaters ratings and satisfies the filter criteria. This method will
        need to create the ArrayList of type String of movie IDs from the MovieDatabase using the
         filterBy method before calculating those averages.
         */
        ArrayList<Rating> ratingArrayList = new ArrayList<Rating>();
        ArrayList<String> movieList = MovieDatabase.filterBy(criteriaFilter);
        for (int index = 0; index < movieList.size(); index++) {
            double averageRating = getAverageByID(movieList.get(index), minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(movieList.get(index), averageRating));
            }
        }
        return ratingArrayList;
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        double totalRatings = 0.0;
        int numberOfRatings = 0;
        for (EfficientRater rater : myRaters) {
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
}