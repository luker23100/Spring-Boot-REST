package com.dev.RestApiStudy.Controller;

import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Service.PersonService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/people")
    public CollectionModel<EntityModel<Person>> findAll() {
        return service.findAll();
    }

    @GetMapping("/people/{id}")
    public EntityModel<Person> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/people")
    public ResponseEntity<?> addPerson(@RequestBody Person newPerson) {
        return service.addPerson(newPerson);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return service.updatePerson(newPerson, id);
    }

    @DeleteMapping("/people")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        return service.deletePerson(id);
    }
}
