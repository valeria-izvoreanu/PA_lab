package Repository;

import DAO.GenresEntity;

import java.util.List;

public interface GenreRepository {
    void create(GenresEntity movie);

    GenresEntity findByID(int id);

    List<GenresEntity> findByName(String title);
}
