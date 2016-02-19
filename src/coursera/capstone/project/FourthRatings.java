package coursera.capstone.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by greg on 2/15/2016.
 */
public class FourthRatings {
    public FourthRatings() {
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
        for (String aMovieList : movieList) {
            double averageRating = getAverageByID(aMovieList, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(aMovieList, averageRating));
            }
        }
        return ratingArrayList;
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        double totalRatings = 0.0;
        int numberOfRatings = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
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

    private double dotProduct(Rater me, Rater rater) {
        double result = 0.0;
        for (String movieId : me.getItemsRated()) {
            if (rater.hasRating(movieId)) {
                result += (me.getRating(movieId) - 5) * (rater.getRating(movieId) - 5);
            }
        }
        return result;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(me.getID()) && dotProduct(rater, me) >= 0.0) {
                list.add(new Rating(rater.getID(), dotProduct(rater, me)));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        /*
        This method should return an ArrayList of type Rating, of movies and their weighted average ratings using
         only the top numSimilarRaters with positive ratings and including only those movies that have at least
         minimalRaters ratings from those top raters. These Rating objects should be returned in sorted order by
         weighted average rating from largest to smallest ratings. This method is very much like the getAverageRatings
         method you have written previously. In particular this method should:

        For every rater, get their similarity rating to the given parameter rater id. Include only those raters with
         positive similarity ratings—those that are more similar to rater id. Which method could you call?

        For each movie, calculate a weighted average movie rating based on: Use only the top (largest) numSimilarRaters
         raters. For each of these raters, multiply their similarity rating by the rating they gave that movie. This
         will emphasize those raters who are closer to the rater id, since they have greater weights. The weighted
         average movie rating for a particular movie is the sum of these weighted average ratings (for each rater
         multiply their similarity rating by their rating for the movie), divided by the total number of such ratings.

        This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.
         */

        //for every rater, get their similarity rating to the given parameter id
        ArrayList<Rating> similarRaterList = getSimilarities(id);

        //for each movie

        //calculate a weighted average movie rating based on

        //use only the top numSimilarRaters raters

        //for each of these raters

        //multiply their similarity rating by the rating they gave that movie

        //the weighted average movie rating for a particular movie is the sum of these weighted average ratings
        //  divided by the total number of such ratings

        //this method returns an ArrayList of Ratings for movies and their calculated weighted ratings in descending
        //  order

        return null;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filterCriteria) {
        /*
        Is similar to the getSimilarRatings method but has one additional Filter parameter named filterCriteria
        and uses that filter to access and rate only those movies that match the filter criteria.
         */
        return null;
    }

    public static void main(String[] args) {
        //String shortMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmovies_short.csv";
        //String shortRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        //String shortRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings_short.csv";
        String bigMovieCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        //String bigMovieCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratedmoviesfull.csv";
        String bigRatingsCsv = "C:/Users/greg/IdeaProjects/capstone-coursera/data/ratings.csv";
        //String bigRatingsCsv = "/home/greg/IdeaProjects/capstone-coursera/data/ratings.csv";

        //MovieDatabase.initialize(shortMovieCsv);
        MovieDatabase.initialize(bigMovieCsv);
        //RaterDatabase.initialize(shortRatingsCsv);
        RaterDatabase.initialize(bigRatingsCsv);

        Rater rater_id_2 = RaterDatabase.getRater("2");
        Rater rater_id_4 = RaterDatabase.getRater("4");

        FourthRatings fourthRatings = new FourthRatings();

        double result = fourthRatings.dotProduct(rater_id_2, rater_id_4);
        System.out.println("Dot product for raters 2 and 4: " + result);
        ArrayList<Rating> similarities = fourthRatings.getSimilarities("2");
        System.out.println("List of raters closest to ID 2 and their dot product:");
        for (Rating rating : similarities) {
            System.out.println(rating.getItem() + " " + rating.getValue());
        }
    }
}
