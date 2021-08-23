package com.dev.RestApiStudy.Controller;

import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/people")
    List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("/people/{id}")
    Person findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/people")
    Person addPerson(@RequestBody Person newPerson) {
        return service.addPerson(newPerson);
    }

    @PutMapping("/people/{id}")
    Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return service.updatePerson(newPerson, id);
    }

    @DeleteMapping("/people")
    void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
}
