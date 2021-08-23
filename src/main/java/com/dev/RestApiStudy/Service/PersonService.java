package com.dev.RestApiStudy.Service;

import com.dev.RestApiStudy.Controller.PersonController;
import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Entity.Statuses.dataStatus;
import com.dev.RestApiStudy.Repository.PersonRepository;
import com.dev.RestApiStudy.Service.Exceptions.PersonNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public CollectionModel<EntityModel<Person>> findAll() {
        List<EntityModel<Person>> people = repository.findAll().stream()
                .map(person ->
                    EntityModel.of(person,
                            linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel(),
                            linkTo(methodOn(PersonController.class).findAll()).withRel("people")))
                .collect(Collectors.toList());

        return CollectionModel.of(people,
                linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
    }

    public EntityModel<Person> findById(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAll()).withRel("people"));
    }

    public Person addPerson(Person newPerson) {
        return repository.save(newPerson);
    }

    public Person updatePerson(Person newPerson, Long id) {
        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    if(newPerson.getDateOfBirth() != null){
                        person.setDateOfBirth(newPerson.getDateOfBirth());
                        person.setStatus(dataStatus.INFORMATION_COMPLETED);
                    }

                    return repository.save(person);
                })
                .orElse(repository.save(newPerson));
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
