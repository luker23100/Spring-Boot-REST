package com.dev.RestApiStudy.Service.EntityModelAssembler;

import com.dev.RestApiStudy.Controller.PersonController;
import com.dev.RestApiStudy.Entity.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntityModelPersonAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAll()).withRel("people"));
    }
}
