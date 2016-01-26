package test;

import coursera.capstone.project.*;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.*;
import edu.duke.*;

import java.util.ArrayList;

public class TestFirstRatings {
    @Test
    public void testLoadMovies() {
        String filename = "..\\..\\data\\ratedmovies_short.csv";
        FirstRatings firstRatings = new FirstRatings();
        ArrayList<Movie> movieArrayList = firstRatings.loadMovies(filename);
        assertEquals(5,movieArrayList.size());

    }
}