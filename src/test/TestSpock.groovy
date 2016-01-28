package test

import coursera.capstone.project.FirstRatings
import coursera.capstone.project.Movie
import coursera.capstone.project.Rater;

class SpockTestOne extends spock.lang.Specification {
    FirstRatings firstRatings
    String short_movie_filename, long_movie_filename, short_rating_filename, long_rating_filename
    ArrayList<Movie> movieArrayList
    ArrayList<Rater> raterArrayList

    def setup() {
        short_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv"
        long_movie_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv"
        short_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv"
        long_rating_filename = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv"
        firstRatings = new FirstRatings()

    }

    def "test-load-small"() {
        given: "loading sample movie data into an array"
        movieArrayList = firstRatings.loadMovies(short_movie_filename)

        when: "getting the size of the array"
        def movieSize = movieArrayList.size()

        then: "expect the size to be 5"
        movieSize == 5
    }
}