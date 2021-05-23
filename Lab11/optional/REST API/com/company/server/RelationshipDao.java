package com.company.server;

import java.util.List;

public interface RelationshipDao {
    void addRelationship(Person person1, Person person2);

    List<Person> getRelationships(Person person);

    List<Person> getMostPopular();

    List<Person> getLeastPopular();
}
