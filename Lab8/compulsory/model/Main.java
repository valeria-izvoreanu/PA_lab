package model;

import DAO.Genre;
import DAO.Movie;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        Movie movie = new Movie();
        movie.createMovie(dataBase.getCon(), 3, "Boy", Date.valueOf("2015-03-31"), 122, 10);
        movie.findById(dataBase.getCon(), 2);
        movie.findByName(dataBase.getCon(), "Heathers");

        Genre genre = new Genre();
        genre.createGenre(dataBase.getCon(), 2, "Action");
        genre.findById(dataBase.getCon(), 1);
        genre.findByName(dataBase.getCon(), "Romance");
        movie.addGenre(dataBase.getCon(), 3, "Boy", "Action");
    }
}
