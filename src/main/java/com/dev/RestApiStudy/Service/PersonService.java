package com.dev.RestApiStudy.Service;

import com.dev.RestApiStudy.Controller.PersonController;
import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Entity.Statuses.dataStatus;
import com.dev.RestApiStudy.Repository.PersonRepository;
import com.dev.RestApiStudy.Service.EntityModelAssembler.EntityModelPersonAssembler;
import com.dev.RestApiStudy.Service.Exceptions.PersonNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final PersonRepository repository;
    private final EntityModelPersonAssembler assembler;

    public PersonService(PersonRepository repository, EntityModelPersonAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<Person>> findAll() {
        List<EntityModel<Person>> people = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(people,
                linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
    }

    public EntityModel<Person> findById(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);
    }

    public ResponseEntity<?> addPerson(Person newPerson) {
        EntityModel<Person> personModel = assembler.toModel(repository.save(newPerson));

        return ResponseEntity
                .created(personModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(personModel);
    }

    public ResponseEntity<?> updatePerson(Person newPerson, Long id) {
        Person updatedPerson = repository.findById(id)
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

        EntityModel<Person> personModel = assembler.toModel(updatedPerson);
        return ResponseEntity
                .created(personModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(personModel);
    }

    public ResponseEntity<?> deletePerson(Long id) {
        repository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
