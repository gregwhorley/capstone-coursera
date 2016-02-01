package test;

import coursera.capstone.project.MovieRunnerAverage;
import org.junit.Test;

/**
 * Created by greg on 1/31/2016.
 */
public class TestMovieRunnerAverage {
    @Test
    public void testPrintAverageRatings() {
        int minimalRaters = 3;
        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        movieRunnerAverage.printAverageRatings(minimalRaters);
    }

    @Test
    public void testGetAverageRatingOneMovie() {
        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        movieRunnerAverage.getAverageRatingOneMovie();
    }
}
