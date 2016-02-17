package coursera.capstone.project;

// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating> {
    private String myId;
    private double myRating;

    public Rating (String anItem, double aValue) {
        myId = anItem;
        myRating = aValue;
    }

    // Returns item being rated
    public String getItem () {
        return myId;
    }

    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue () {
        return myRating;
    }

    // Returns a string of all the rating information
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (myRating < other.myRating) return -1;
        if (myRating > other.myRating) return 1;
        
        return 0;
    }
}
