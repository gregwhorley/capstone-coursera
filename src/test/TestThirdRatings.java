package test;

import coursera.capstone.project.Filter;
import coursera.capstone.project.Rating;
import coursera.capstone.project.ThirdRatings;
import coursera.capstone.project.TrueFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by greg on 2/9/2016.
 */
public class TestThirdRatings {
    protected ThirdRatings thirdRatings;
    protected String short_rating_filename, long_rating_filename;
    protected Filter filter;

    @Before
    public void setUp() {
        short_rating_filename = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        long_rating_filename = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
    }

    @Test
    public void testGetAverageRatingsByFilter() {
        filter = new TrueFilter();
        int minimalRaters = 1;
        thirdRatings = new ThirdRatings(short_rating_filename);
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);
        for (Rating rating : averageRatings) {
            System.out.println("MovieID: " + rating.getItem() + "\tRating: " + rating.getValue());
        }
    }
}
