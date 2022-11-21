package ru.ivanov.lib.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.ivanov.lib.models.Book;
import ru.ivanov.lib.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivanov Alexandr on 20.11.2022
 */
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setDateOfWriting((rs.getInt("dateofwriting")));
        book.setAuthor(rs.getString("author"));
        return book;
    }
}
