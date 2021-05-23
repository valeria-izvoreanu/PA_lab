package com.company.server;

import java.util.List;

public interface PersonDao {
    List<Person> findAll();

    void insertPerson(Person person);

    void updateName(Person person, String name);

    void deletePerson(Person emp);

    Person findById(int id);

}
