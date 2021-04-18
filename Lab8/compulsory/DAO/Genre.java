package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre {
    public Genre() {
    }

    public Genre(Connection con, int id, String name) {
        createGenre(con, id, name);
    }

    public void createGenre(Connection con, int id, String name) {
        try {
            String sql = "INSERT INTO genres VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create genre:" + e);
        }
    }

    public boolean findById(Connection con, int id) {
        try {
            String sql = "SELECT * FROM genres WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
                return false;
            }
            while (rs.next()) {
                System.out.println(rs.getString("nume"));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find:" + e);
            return false;
        }
    }

    public int findByName(Connection con, String name) {
        try {
            String sql = "SELECT * FROM genres WHERE nume = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
                return -1;
            }
            if (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("nume"));
            }
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find:" + e);
            return -1;
        }
    }
}
