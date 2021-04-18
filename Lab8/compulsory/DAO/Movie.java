package DAO;

import java.sql.*;

public class Movie {
    public Movie() {
    }

    public Movie(Connection con, int id, String title, Date release_date, int duration, int score) {
        createMovie(con, id, title, release_date, duration, score);
    }

    public void createMovie(Connection con, int id, String title, Date release_date, int duration, int score) {
        try {
            String sql = "INSERT INTO movies VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setDate(3, release_date);
            pstmt.setInt(4, duration);
            pstmt.setInt(5, score);
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
                System.out.println("No data");
                return false;
            }
            while (rs.next()) {
                System.out.println(rs.getString("title"));
            }
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
                System.out.println("No data");
                return -1;
            }
            if (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("title") + " " + rs.getDate("release_date"));
            }
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find movie title:" + e);
            return -1;
        }
    }

    //add genre by finding movie and genre by id
    public void addGenre(Connection con, int id, int id_movie, int id_genre) {
        //if movie and genre exist, add it into collections table
        if (findById(con, id_movie) && findById(con, id_genre)) {
            try {
                String sql = "INSERT INTO collections VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, id_movie);
                pstmt.setInt(3, id_genre);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println("Couldn't add genre:" + e);
            }
        } else {
            System.out.println("Movie or Genre doesn't exist");
        }
    }

    public void addGenre(Connection con, int id, String title, String genre_name) {
        //find id of movie and genre by name
        //if they exist
        int id_movie = findByName(con, title);
        Genre genre = new Genre();
        int id_genre = genre.findByName(con, genre_name);
        if (id_movie != -1 && id_genre != -1) {
            try {
                String sql = "INSERT INTO collections VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, id_movie);
                pstmt.setInt(3, id_genre);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println("Couldn't add genre:" + e);
            }
        } else {
            System.out.println("Movie or Genre doesn't exist");
        }
    }
}
