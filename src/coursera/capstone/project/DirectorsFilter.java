package coursera.capstone.project;

/**
 * Created by greg on 2/10/2016.
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for (int index = 0; index < myDirectors.length; index++) {
            if (MovieDatabase.getDirector(id).contains(myDirectors[index])) {
                return true;
            }
        }
        return false;
    }
}
