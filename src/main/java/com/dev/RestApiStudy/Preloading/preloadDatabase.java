package com.dev.RestApiStudy.Preloading;

import com.dev.RestApiStudy.Entity.Person;
import com.dev.RestApiStudy.Repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class preloadDatabase {
    private static final Logger log = LoggerFactory.getLogger(preloadDatabase.class);

    @Bean
    CommandLineRunner loadDatabase(PersonRepository repository) {
        return args -> {
            log.info("Loading person: " + repository.save(new Person("Mr", "Tester1")));
            log.info("Loading person: " + repository.save(new Person("Mrs", "Tester2", LocalDate.of(1998, Month.FEBRUARY, 3))));
            log.info("Loading person: " + repository.save(new Person("Mr", "Tester3")));
        };
    }
}
