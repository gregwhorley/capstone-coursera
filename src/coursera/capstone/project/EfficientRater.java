package coursera.capstone.project;

/**
 * Created by greg on 2/3/16.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    @Override
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    @Override
    public boolean hasRating(String item) {
        for (String movieId : myRatings.keySet()) {
            if (movieId.contains(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getID() {
        return myID;
    }

    @Override
    public double getRating(String item) {
        for (String movieId : myRatings.keySet()) {
            if (movieId.contains(item)) {
                return myRatings.get(item).getValue();
            }
        }
        return -1;
    }

    @Override
    public int numRatings() {
        return myRatings.size();
    }

    @Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String movieId : myRatings.keySet()) {
            list.add(movieId);
        }
        return list;
    }
}