package DAO;

import java.sql.*;

public class Director {
    public Director() {
    }

    public Director(Connection con, String firstName, String lastName, Date birthday) {
        createDirector(con, firstName, lastName, birthday);
    }

    public void createDirector(Connection con, String firstName, String lastName, Date birthday) {
        try {
            String sql = "INSERT INTO actors VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, birthday);
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create director:" + e);
        }
    }

    public int findByName(Connection con, String firstName, String lastName) {
        try {
            String sql = "SELECT * FROM directors WHERE first_name = ? AND last_name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                return -1;
            }
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find director:" + e);
            return -1;
        }
    }

    public void addDirectorToMovie(Connection con, String firstName, String lastName, String title) {
        int id_director = findByName(con, firstName, lastName);
        Movie movie = new Movie();
        int id_movie = movie.findByName(con, title);
        if (id_movie != -1 && id_director != -1) {
            try {
                String sql = "INSERT INTO movie_directors VALUES (?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id_movie);
                pstmt.setInt(2, id_director);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println("Couldn't add director and movie:" + e);
            }
        } else {
            System.out.println("Director or Movie doesn't exist");
        }
    }
}
