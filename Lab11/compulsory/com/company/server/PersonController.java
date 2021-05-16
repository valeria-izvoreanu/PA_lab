package com.company.server;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    PersonDaoImpl people = new PersonDaoImpl();

    @GetMapping
    public List<Person> getAllPersons() {
        return people.findAll();
    }

    @PostMapping
    public void createPerson(@RequestBody String name) {
        people.insertPerson(new Person(name));
    }

    @PutMapping("/{name}")
    public void updatePerson(@RequestBody String newName, @PathVariable("name") String name) {
        people.updateName(new Person(name), newName);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        Person person = people.findById(id);
        people.deletePerson(person);
    }
}
