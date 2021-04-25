package DAO;

import java.sql.*;

public class Actor {
    public Actor() {
    }

    public Actor(Connection con, String firstName, String lastName, Date birthday) {
        createActor(con, firstName, lastName, birthday);
    }

    public void createActor(Connection con, String firstName, String lastName, Date birthday) {
        try {
            String sql = "INSERT INTO actors VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, birthday);
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create actor:" + e);
        }
    }

    public int findByName(Connection con, String firstName, String lastName) {
        try {
            String sql = "SELECT * FROM actors WHERE first_name = ? AND last_name = ?";
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
            System.out.println("Couldn't execute select to find actor:" + e);
            return -1;
        }
    }

    public void addActorToMovie(Connection con, String firstName, String lastName, String title) {
        int id_actor = findByName(con, firstName, lastName);
        Movie movie = new Movie();
        int id_movie = movie.findByName(con, title);
        if (id_movie != -1 && id_actor != -1) {
            try {
                String sql = "INSERT INTO movie_actors VALUES (?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id_movie);
                pstmt.setInt(2, id_actor);
                pstmt.execute();
            } catch (SQLException e) {
                System.out.println("Couldn't add actor and movie:" + e);
            }
        } else {
            System.out.println("Actor or Movie doesn't exist");
        }
    }
}
