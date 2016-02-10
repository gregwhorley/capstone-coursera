package coursera.capstone.project;

/**
 * Created by greg on 2/10/2016.
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }

    @Override
    public boolean satisfies(String id) {
        return myDirectors.contains(MovieDatabase.getDirector(id));
    }
}
