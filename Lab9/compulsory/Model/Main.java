package Model;


import DAO.MoviesEntity;
import Repository.GenreRepositoryImplement;
import Repository.MovieRepositoryImplement;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        DatabaseEntityManagerFactory dataBase = DatabaseEntityManagerFactory.getInstance();

        GenreRepositoryImplement genreImpl = new GenreRepositoryImplement(dataBase.getEm());
        System.out.println(genreImpl.findByID(2));
        System.out.println(genreImpl.findByName("Action"));

        MoviesEntity moviesEntity = new MoviesEntity();
        moviesEntity.setId(2);
        moviesEntity.setTitle("Boys");
        moviesEntity.setScore(10);
        moviesEntity.setMovieLength(100);
        moviesEntity.setReleaseDate(Date.valueOf("2015-03-31"));
        moviesEntity.addGenre(genreImpl.findByID(2));
        MovieRepositoryImplement moviesImpl = new MovieRepositoryImplement(dataBase.getEm());
        moviesImpl.create(moviesEntity);
    }
}
