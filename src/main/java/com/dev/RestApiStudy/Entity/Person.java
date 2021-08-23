package com.dev.RestApiStudy.Entity;

import com.dev.RestApiStudy.Entity.Statuses.dataStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Person {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private dataStatus status;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        status = dataStatus.INFORMATION_IN_PROGRESS;
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        status = dataStatus.INFORMATION_COMPLETED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public dataStatus getStatus() {
        return status;
    }

    public void setStatus(dataStatus status) {
        this.status = status;
    }
}
