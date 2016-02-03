package coursera.capstone.project;

import java.util.ArrayList;

/**
 * Created by greg on 2/3/16.
 */
public interface Rater {
    void addRating(String item, double rating);

    boolean hasRating(String item);

    String getID();

    double getRating(String item);

    int numRatings();

    ArrayList<String> getItemsRated();
}
