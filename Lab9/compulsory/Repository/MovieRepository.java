package Repository;

import DAO.MoviesEntity;

import java.util.List;

public interface MovieRepository {
    void create(MoviesEntity movie);

    MoviesEntity findByID(int id);

    List<MoviesEntity> findByName(String title);
}
