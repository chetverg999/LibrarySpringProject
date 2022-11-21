package ru.ivanov.lib.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.lib.models.Person;
import java.util.List;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;  // экзепляр драйвера базы даннных из контекста

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) { // внедрение подключения к базе данных
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() { // просмотр всех людей
        return jdbcTemplate.query("SELECT * FROM person", new  PersonMapper()
//                new BeanPropertyRowMapper<>(Person.class)
               );
    }

    public Person show(int id) { // просмотр конкретно человека по его айди
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                        new Object[]{id}, new PersonMapper()
//                        new BeanPropertyRowMapper<>(Person.class)
                )
                .stream().findAny().orElse(null);
    }


    public void edit(Person person) { // добавление человека в базу данных
        jdbcTemplate.update("INSERT INTO person(name, dateofbirth) VALUES(?, ?)", person.getName(), person.getDateOfBirth());
    }

    public void update(int id, Person person) { // изменение человека в базе данных
        jdbcTemplate.update("UPDATE person SET name=?, dateofbirth=? WHERE person_id=?",
                person.getName(), person.getDateOfBirth(), id);
    }

    public void delete(int id) { // удаление человека из базы данных
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

}
