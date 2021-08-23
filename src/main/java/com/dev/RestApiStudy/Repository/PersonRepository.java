package com.dev.RestApiStudy.Repository;

import com.dev.RestApiStudy.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
