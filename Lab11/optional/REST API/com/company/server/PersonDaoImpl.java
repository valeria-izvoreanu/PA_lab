package com.company.server;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    Connection con;

    //create database instance for person entity
    public PersonDaoImpl() {
        DataBase dataBase = DataBase.getInstance();

        this.con = dataBase.getCon();
    }

    @Override
    public List<Person> findAll() {
        try {
            //query to get all people from database
            String sql = "SELECT * FROM persons";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            //add result to a list of persons
            List<Person> list = new ArrayList<>();
            while (result.next()) {
                Person person = new Person();

                person.setName(result.getString("name"));
                person.setId(result.getInt("id"));
                list.add(person);
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select:" + e);
            return null;
        }
    }

    @Override
    public Person findById(int id) {
        try {
            String sql = "SELECT * FROM persons WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            rs.next();
            Person person = new Person(rs.getString("name"));
            person.setId(rs.getInt("id"));
            return person;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select to find id:" + e);
            return null;
        }
    }


    @Override
    public void insertPerson(Person person) {
        try {
            String sql = "INSERT INTO persons(name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, person.getName());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create person:" + e);
        }
    }

    @Override
    public void updateName(Person person, String newName) {

        try {
            String sql = "UPDATE persons set name = ? where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, person.getName());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create person:" + e);
        }

    }

    @Override
    public void deletePerson(Person person) {

        try {
            String sql = "DELETE  from persons where name = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, person.getName());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create person:" + e);
        }

    }

}
