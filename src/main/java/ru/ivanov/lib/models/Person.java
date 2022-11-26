package ru.ivanov.lib.models;

import javax.validation.constraints.*;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z][a-zA-Z\\s]{2,}", message = "Имя должно быть валидным")
    private String name;

    @NotNull(message = "Year of birth should not be empty")
    @Min(value = 1900, message = "Year of writing should be valid")
    @Max(value = 2022, message = "Year of birth should be valid")
    private int dateOfBirth;

    public Person() {
    }

    public Person(int person_id, String name, int dateOfBirth) {
        this.person_id = person_id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return person_id;
    }

    public void setId(int person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + person_id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
