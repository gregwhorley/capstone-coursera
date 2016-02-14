package coursera.capstone.project;

/**
 * Created by greg on 2/9/2016.
 */
public class MinutesFilter implements Filter {
    private int myMinMinutes;
    private int myMaxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id) >= myMinMinutes) &&
                (MovieDatabase.getMinutes(id) <= myMaxMinutes);
    }
}
