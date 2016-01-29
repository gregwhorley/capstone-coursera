package test;

import coursera.capstone.project.Rating;
import coursera.capstone.project.SecondRatings;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by greg on 1/27/16.
 */
public class TestSecondRatings {
    protected SecondRatings secondRatings;
    protected String short_movie_filename, long_movie_filename, short_rating_filename, long_rating_filename;

    @Before
    public void setUp() {
        short_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        long_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        short_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        long_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
    }

    @Test
    public void testSecondRatingsFields() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        System.out.println("Number of movies loaded from file: " + secondRatings.getMovieSize());
        System.out.println("Number of raters loaded from file: " + secondRatings.getRaterSize());
        assertEquals(5, secondRatings.getMovieSize());
        assertEquals(5, secondRatings.getRaterSize());
    }

    @Test
    public void testGetAverageRatings() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        String validMovieId = "0068646";
        int minimalRatings = 4;
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRatings);
        double averageRating = getRatingForMovieId(ratingArrayList, validMovieId);
        System.out.println("Average rating for movie id (" + validMovieId + ") with rating threshold" +
                " of " + minimalRatings + " : " + averageRating);
        assertEquals(9.0, averageRating);
    }

    @Test
    public void testGetAverageRatingsWithInvalidMovieId() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        String invalidMovieId = "123456";
        int minimalRatings = 1;
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRatings);
        double averageRating = getRatingForMovieId(ratingArrayList, invalidMovieId);
        System.out.println("Return value for invalid movie ID should be 0.0 - actual value: " + averageRating);
        assertEquals(0.0, averageRating);
    }

    @Test
    public void testGetAverageRatingsWithInvalidMinimalRating() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        String validMovieId = "0068646";
        int minimalRatings = 10;
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRatings);
        double averageRating = getRatingForMovieId(ratingArrayList, validMovieId);
        System.out.println("Return value for valid movie ID and invalid rating threshold should be 0.0" +
                " - actual value: " + averageRating);
        assertEquals(0.0, averageRating);
    }

    @Test
    public void testGetTitle() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        String validMovieId = "0068646";
        String movieTitle = secondRatings.getTitle(validMovieId);
        System.out.println("Movie title for ID (" + validMovieId + ") is: " + movieTitle);
        assertEquals("The Godfather", movieTitle);
    }

    @Test
    public void testGetTitleWithInvalidId() {
        secondRatings = new SecondRatings(short_movie_filename, short_rating_filename);
        String invalidMovieId = "123456";
        String movieTitle = secondRatings.getTitle(invalidMovieId);
        System.out.println("Invalid movie ID should return ID not found! - actual: " + movieTitle);
        assertEquals("ID not found! " + invalidMovieId, movieTitle);
    }

    private double getRatingForMovieId(ArrayList<Rating> ratingArrayList, String movieId) {
        for (Rating rating : ratingArrayList) {
            if (rating.getItem().contains(movieId)) {
                return rating.getValue();
            }
        }
        return 0.0;
    }
}
