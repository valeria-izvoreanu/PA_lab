package DAO;

import java.sql.*;

public class Movie {
    private String title;
    private Date release_date;
    private int duration;
    private int score;

    public Movie() {
    }

    public Movie(String title, Date release_date, int duration, int score) {
        this.title = title;
        this.release_date = release_date;
        this.duration = duration;
        this.score = score;
    }

    public Movie(Connection con, String title, Date release_date, int duration, int score) {
        createMovie(con, title, release_date, duration, score);
    }

    public String getTitle() {
        return title;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    public void createMovie(Connection con, String title, Date release_date, int duration, int score) {
        try {
            String sql = "INSERT INTO movies(title, release_date, movie_length, score) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setDate(2, release_date);
            pstmt.setInt(3, duration);
            pstmt.setInt(4, score);
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create movie:" + e);
        }
    }

    public boolean findById(Connection con, int id) {
        try {
            String sql = "SELECT * FROM movies WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            //execute select if it has no results return false
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                return false;
            }
            rs.next();
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find id:" + e);
            return false;
        }
    }

    public int findByName(Connection con, String name) {
        try {
            String sql = "SELECT * FROM movies WHERE title = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                return -1;
            }
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find movie title:" + e);
            return -1;
        }
    }


    public void addGenre(Connection con, String title, String genre_name) {
        //find id of movie and genre by name
        //if they exist
        int id_movie = findByName(con, title);
        Genre genre = new Genre();
        int id_genre = genre.findByName(con, genre_name);
        if (id_movie != -1 && id_genre != -1) {
            try {
                String sql = "INSERT INTO collections VALUES (?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id_movie);
                pstmt.setInt(2, id_genre);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println("Couldn't add genre:" + e);
            }
        } else {
            System.out.println("Movie or Genre doesn't exist");
        }
    }
}
