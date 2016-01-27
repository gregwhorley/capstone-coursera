package test;

import coursera.capstone.project.SecondRatings;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by greg on 1/27/16.
 */
public class TestSecondRatings extends TestCase {
    protected SecondRatings secondRatings;
    protected String short_movie_filename, long_movie_filename, short_rating_filename, long_rating_filename;

    protected void setUp() {
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
}
