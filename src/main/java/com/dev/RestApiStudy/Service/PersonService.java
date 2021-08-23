package com.dev.RestApiStudy.Service;

import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Entity.Statuses.dataStatus;
import com.dev.RestApiStudy.Repository.PersonRepository;
import com.dev.RestApiStudy.Service.Exceptions.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
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
