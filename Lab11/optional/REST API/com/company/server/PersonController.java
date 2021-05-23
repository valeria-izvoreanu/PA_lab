package com.company.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    PersonDaoImpl people = new PersonDaoImpl();

    @GetMapping
    public List<Person> getAllPersons() {
        List<Person> personList = people.findAll();
        if (personList.isEmpty()) {
            throw new MyException("No People Found");
        }
        return personList;
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody String name) {
        people.insertPerson(new Person(name));
        return new ResponseEntity<>(
                "Person registered successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public void updatePerson(@RequestBody String newName, @PathVariable("name") String name) {
        people.updateName(new Person(name), newName);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        Person person = people.findById(id);
        if (person == null) {
            throw new MyException("Person not found");
        }
        people.deletePerson(person);
    }
}
