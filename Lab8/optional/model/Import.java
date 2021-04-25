package model;

import DAO.Genre;
import DAO.Movie;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Import {
    public static void importMovies(Connection con) {
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:/0Sem_2/PA/archive/IMDb movies2.csv"));
            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(inputStreamReader);

            for (CSVRecord record : csvParser) {
                Movie movie = new Movie();
                movie.createMovie(con, record.get("title"), Date.valueOf(record.get("date_published")),
                        Integer.parseInt(record.get("duration")), Math.round(Float.valueOf(record.get("avg_vote"))));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void importGenres(Connection con) {
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:/0Sem_2/PA/archive/IMDb movies.csv"));
            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(inputStreamReader);
            //get all genres of each movie and check if genre wasn't already added
            //then add the movie and it's genres to the association table
            for (CSVRecord record : csvParser) {
                Genre genre = new Genre();
                String title = record.get("genre");
                String[] posibleGenres = title.split(", ");
                Movie movie = new Movie();
                for (String s : posibleGenres) {
                    if (genre.findByName(con, s) == -1) genre.createGenre(con, s);
                    movie.addGenre(con, record.get("title"), s);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void report(Connection con) {
        try {
            //Freemarker configuration object
            Configuration cfg = new Configuration(new Version("2.3.31"));
            FileTemplateLoader ftl = new FileTemplateLoader(new File("src"));
            cfg.setTemplateLoader(ftl);
            //Load template from source folder
            Template template = cfg.getTemplate("Template.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<>();
            List<Movie> movies = new ArrayList<>();

            //query to get all data form the database
            String sql = "SELECT * FROM movies";
            PreparedStatement pstmt = con.prepareStatement(sql);
            //execute select  and add results to movie list
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getString("title"), rs.getDate("release_date"),
                        rs.getInt("movie_length"), rs.getInt("score"));
                movies.add(movie);
            }
            data.put("movies", movies);
            // File output
            Writer file = new FileWriter("d:/0Sem_2/PA/MyMovies.html");
            template.process(data, file);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
