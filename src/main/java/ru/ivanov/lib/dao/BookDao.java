package ru.ivanov.lib.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.lib.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper()
//                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id},
                new BookMapper())
//                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public List<Book> showBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
                        new Object[]{id}, new BookMapper());
    }


    public void edit(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, dateOfWriting) VALUES (?, ?, ?)",
                 book.getTitle(), book.getAuthor(), book.getDateOfWriting());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, dateofwriting=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getDateOfWriting(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }


    public void deletePerson(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
    }
}
