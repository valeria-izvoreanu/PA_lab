package model;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        Import.importMovies(dataBase.getCon());
        Import.importGenres(dataBase.getCon());
        Import.report(dataBase.getCon());
    }
}
