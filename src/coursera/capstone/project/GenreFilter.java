package coursera.capstone.project;

/**
 * Created by greg on 2/9/2016.
 */
public class GenreFilter implements Filter {
    String myGenre;

    public GenreFilter(String genre) {
        myGenre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}
