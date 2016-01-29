package test;

import coursera.capstone.project.FirstRatings;
import coursera.capstone.project.Movie;
import coursera.capstone.project.Rater;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;


public class TestFirstRatings {
    protected FirstRatings firstRatings;
    protected String short_movie_filename, long_movie_filename, short_rating_filename, long_rating_filename;
    protected ArrayList<Movie> movieArrayList;
    protected ArrayList<Rater> raterArrayList;

    @Before
    public void setUp() {
        short_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        long_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        short_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        long_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        firstRatings = new FirstRatings();
    }

    @Test
    public void testLoadMoviesSmallArraySize() {
        movieArrayList = firstRatings.loadMovies(short_movie_filename);
        assertEquals(5,movieArrayList.size());
    }

    @Test
    public void testLoadMoviesLargeArraySize() {
        movieArrayList = firstRatings.loadMovies(long_movie_filename);
        assertEquals(3143, movieArrayList.size());
    }

    @Test
    public void testComedyGenreInArray() {
        movieArrayList = firstRatings.loadMovies(short_movie_filename);
        int numberOfComedies = 0;
        for (Movie movie : movieArrayList) {
            if (movie.getGenres().contains("Comedy")) {
                numberOfComedies++;
            }
        }
        assertEquals(1, numberOfComedies);
    }

    @Test
    public void testMovieLengthInArray() {
        movieArrayList = firstRatings.loadMovies(short_movie_filename);
        int moviesGreaterThan150Minutes = 0;
        for (Movie movie : movieArrayList) {
            if (movie.getMinutes() > 150) {
                moviesGreaterThan150Minutes++;
            }
        }
        assertEquals(2, moviesGreaterThan150Minutes);
    }

    @Test
    public void testMaxNumberOfDirectors() {
        movieArrayList = firstRatings.loadMovies(short_movie_filename);
        HashMap<String, Integer> directorMap = getMapOfDirectorsToNumberOfFilms(movieArrayList);
        String biggestDirectorName = getDirectorWithMostFilms(directorMap);
        int numberOfFilms = directorMap.get(biggestDirectorName);
        assertEquals(1, numberOfFilms);
    }

    @Test
    public void testShortRatingsFile() {
        raterArrayList = firstRatings.loadRaters(short_rating_filename);
        System.out.println("Total number of raters: " + raterArrayList.size());
        for (Rater rater : raterArrayList) {
            System.out.println("Rater ID: " + rater.getID() + "\tNumber of ratings: " + rater.numRatings());
            ArrayList<String> ratedMovieIds = rater.getItemsRated();
            for (int index = 0; index < ratedMovieIds.size(); index++) {
                System.out.println("Movie ID: " + ratedMovieIds.get(index) + "\tRating: " +
                        rater.getRating(ratedMovieIds.get(index)));
            }
        }
        assertEquals(5, raterArrayList.size());
    }

    @Test
    public void testLongRatingsFile() {
        raterArrayList = firstRatings.loadRaters(long_rating_filename);
        System.out.println("Total number of raters: " + raterArrayList.size());
        assertEquals(1048, raterArrayList.size());
    }

    @Test
    public void testNumOfRatingsById() {
        raterArrayList = firstRatings.loadRaters(short_rating_filename);
        System.out.println("Getting number of ratings for rater_id=2");
        int numRatingsForId = 0;
        for (int index = 0; index < raterArrayList.size(); index++) {
            if (raterArrayList.get(index).getID().contains("2")) {
                numRatingsForId = raterArrayList.get(index).numRatings();
            }
        }
        System.out.println("Number of ratings for rater_id=2: " + numRatingsForId);
        assertEquals(3, numRatingsForId);
    }

    @Test
    public void testMaxNumOfRatingsByAnyRater() {
        raterArrayList = firstRatings.loadRaters(short_rating_filename);
        int mostRatings = findMaxNumOfRatingsInArray(raterArrayList);
        ArrayList<String> ratersWithMostRatings = new ArrayList<String>();
        for (Rater rater : raterArrayList) {
            if (rater.numRatings() == mostRatings) {
                ratersWithMostRatings.add(rater.getID());
            }
        }
        System.out.println("Found maximum number of ratings: " + mostRatings);
        System.out.println("User IDs who have rated the most: " + ratersWithMostRatings);
        assertEquals(3, mostRatings);
    }

    @Test
    public void testNumOfRatingsByMovieId() {
        raterArrayList = firstRatings.loadRaters(short_rating_filename);
        String movieId = "1798709";
        int numberOfRatingsForMovie = 0;
        for (Rater rater : raterArrayList) {
            ArrayList<String> ratedMovies = rater.getItemsRated();
            if (ratedMovies.contains(movieId)) {
                numberOfRatingsForMovie++;
            }
        }
        System.out.println("Found number of ratings for movie ID (" + movieId + "): " + numberOfRatingsForMovie);
        assertEquals(4, numberOfRatingsForMovie);
    }

    @Test
    public void testNumOfMoviesRated() {
        raterArrayList = firstRatings.loadRaters(short_rating_filename);
        ArrayList<String> uniqueRatedMovieIds = new ArrayList<String>();
        for (Rater rater : raterArrayList) {
            ArrayList<String> ratedMovies = rater.getItemsRated();
            for (int index = 0; index < ratedMovies.size(); index++) {
                if (!uniqueRatedMovieIds.contains(ratedMovies.get(index))) {
                    uniqueRatedMovieIds.add(ratedMovies.get(index));
                }
            }
        }
        System.out.println("Found " + uniqueRatedMovieIds.size() + " movies rated by all users.");
        System.out.println("List of movie IDs that have been rated: " + uniqueRatedMovieIds);
        assertEquals(4, uniqueRatedMovieIds.size());
    }

    private int findMaxNumOfRatingsInArray(ArrayList<Rater> raterList) {
        int maxNumOfRatings = 0;
        for (Rater rater : raterList) {
            if (rater.numRatings() > maxNumOfRatings) {
                maxNumOfRatings = rater.numRatings();
            }
        }
        return maxNumOfRatings;
    }

    private HashMap<String, Integer> getMapOfDirectorsToNumberOfFilms(ArrayList<Movie> movieList) {
        HashMap<String, Integer> directorMap = new HashMap<String, Integer>();
        for (Movie movie : movieList) {
            if (!directorMap.containsKey(movie.getDirector())) {
                directorMap.put(movie.getDirector(), 1);
            } else if (directorMap.containsKey(movie.getDirector())) {
                int currentValue = directorMap.get(movie.getDirector());
                directorMap.replace(movie.getDirector(), currentValue, currentValue + 1);
            }
        }
        return directorMap;
    }

    private String getDirectorWithMostFilms(HashMap<String, Integer> directorMap) {
        int biggestDirectorTotal = 0;
        String biggestDirectorName = null;
        for (String director : directorMap.keySet()) {
            if (biggestDirectorName == null && biggestDirectorTotal == 0) {
                biggestDirectorName = director;
                biggestDirectorTotal = directorMap.get(director);
            } else if (directorMap.get(director) > biggestDirectorTotal) {
                biggestDirectorName = director;
                biggestDirectorTotal = directorMap.get(director);
            }
        }
        return biggestDirectorName;
    }

}