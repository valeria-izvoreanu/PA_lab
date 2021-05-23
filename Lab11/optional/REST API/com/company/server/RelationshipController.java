package com.company.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationshipController {
    RelationshipDaoImpl relations = new RelationshipDaoImpl();

    @PostMapping("/{name1}/{name2}")
    public ResponseEntity<String> createRelationship(@PathVariable("name1") String name1, @PathVariable("name2") String name2) {
        Person person1 = new Person(name1);
        Person person2 = new Person(name2);
        relations.addRelationship(person1, person2);
        return new ResponseEntity<>(
                "Relation saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public List<Person> getAllFriends(@PathVariable("name") String name) {
        Person person = new Person(name);
        List<Person> personList = relations.getRelationships(person);
        if (personList.isEmpty()) {
            throw new MyException("No Friends Found");
        }
        return personList;
    }

    @GetMapping("/popular")
    public List<Person> getMostPopular() {
        List<Person> personList = relations.getMostPopular();
        if (personList.isEmpty()) {
            throw new MyException("No People Found");
        }
        return personList;
    }

    @GetMapping("/unpopular")
    public List<Person> getLeastPopular() {
        List<Person> personList = relations.getLeastPopular();
        if (personList.isEmpty()) {
            throw new MyException("No People Found");
        }
        return personList;
    }
}
