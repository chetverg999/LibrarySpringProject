package ru.ivanov.lib.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.ivanov.lib.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivanov Alexandr on 20.11.2022
 */
public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setDateOfBirth((rs.getInt("dateofbirth")));
        return person;
    }
}
