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
    private ArrayList<EfficientRater> myRaters;

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

    public String getTitle(String movieId) {
        for (Movie movie : myMovies) {
            if (movie.getID().contains(movieId)) {
                return movie.getTitle();
            }
        }
        return "ID not found! " + movieId;
    }

    public String getID(String movieTitle) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().contains(movieTitle)) {
                return movie.getID();
            }
        }
        return "No title found that matches: " + movieTitle;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> uniqueMovieIds = getUniqueMovieIds();
        for (int index = 0; index < uniqueMovieIds.size(); index++) {
            double averageRating = getAverageByID(uniqueMovieIds.get(index), minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(uniqueMovieIds.get(index), averageRating));
            }
        }
        return ratingArrayList;
    }

    private ArrayList<String> getUniqueMovieIds() {
        ArrayList<String> uniqueList = new ArrayList<>();
        for (EfficientRater rater : myRaters) {
            ArrayList<String> ratingList = rater.getItemsRated();
            for (int index = 0; index < ratingList.size(); index++) {
                if (!uniqueList.contains(ratingList.get(index))) {
                    uniqueList.add(ratingList.get(index));
                }
            }
        }
        return uniqueList;
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