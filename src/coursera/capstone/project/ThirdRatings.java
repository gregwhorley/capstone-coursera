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
        ArrayList<String> movieIdList = MovieDatabase.filterBy(new TrueFilter());
        for (String movieId : movieIdList) {
            double averageRating = getAverageByID(movieId, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(movieId, averageRating));
            }
        }
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter criteriaFilter) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
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