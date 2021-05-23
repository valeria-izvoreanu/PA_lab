package com.company.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//errors
public class RelationshipDaoImpl implements RelationshipDao {
    Connection con;

    //create database instance for person entity
    public RelationshipDaoImpl() {
        DataBase dataBase = DataBase.getInstance();

        this.con = dataBase.getCon();
    }

    public void addRelationship(Person person1, Person person2) {
        try {
            person1 = findByName(person1.getName());
            person2 = findByName(person2.getName());
            String sql = "INSERT INTO relations(friend1, friend2) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, person1.getId());
            pstmt.setInt(2, person2.getId());
            pstmt.execute();
            pstmt.setInt(2, person1.getId());
            pstmt.setInt(1, person2.getId());
            pstmt.execute();
        } catch (SQLException e) {
            System.err.println("Couldn't create person:" + e);
        }
    }

    public List<Person> getRelationships(Person person) {
        try {
            person = findByName(person.getName());
            String sql = "SELECT id, name FROM persons INNER JOIN relations ON id=friend2 WHERE friend1 = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, person.getId());
            ResultSet result = pstmt.executeQuery();
            List<Person> friends = new ArrayList<>();
            while (result.next()) {
                Person friend = new Person();
                friend.setName(result.getString("name"));
                friend.setId(result.getInt("id"));
                friends.add(friend);
            }

            return friends;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select:" + e);
            return null;
        }
    }

    public List<Person> getMostPopular() {
        try {
            //get number of friends for each person then sort in ascending order
            String sql = "SELECT friend1, count(friend2) FROM persons INNER JOIN relations ON id=friend2" +
                    "Group by friend1 order by count(friend2) desc Limit 3";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            PersonDaoImpl personDao = new PersonDaoImpl();
            List<Person> people = new ArrayList<>();
            while (result.next()) {
                Person person = new Person(personDao.findById(result.getInt("friend1")).getName());
                person.setId(result.getInt("friend1"));
                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select:" + e);
            return null;
        }
    }

    public List<Person> getLeastPopular() {
        try {
            //get number of friends for each person then sort in descending order
            String sql = "SELECT friend1, count(friend2) FROM persons INNER JOIN relations ON id=friend2 " +
                    "Group by friend1 order by count(friend2) asc Limit 3";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            PersonDaoImpl personDao = new PersonDaoImpl();
            List<Person> people = new ArrayList<>();
            while (result.next()) {
                Person person = new Person(personDao.findById(result.getInt("friend1")).getName());
                person.setId(result.getInt("friend1"));
                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            System.out.println("Couldn't execute select:" + e);
            return null;
        }
    }

    public Person findByName(String name) {
        try {
            String sql = "SELECT * FROM persons WHERE name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
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
}
