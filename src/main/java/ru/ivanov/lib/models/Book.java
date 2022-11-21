package ru.ivanov.lib.models;

import javax.validation.constraints.*;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

public class Book {

    private int book_id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 300, message = "Title should be between 1 and 300 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z][a-zA-Z\\s]{2,}", message = "Author should be valid")
    private String author;

    @NotNull(message = "Date of writing should not be empty")
    @Min(value = 1, message ="Date of writing should be valid" )
    @Max(value = 2022, message = "Date of writing should be valid")
    private int dateOfWriting;


    public Book() {
    }

    public Book(int book_id, String title, String author, int dateOfWriting) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.dateOfWriting = dateOfWriting;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDateOfWriting() {
        return dateOfWriting;
    }

    public void setDateOfWriting(int dateOfWriting) {
        this.dateOfWriting = dateOfWriting;
    }

    public int getId() {
        return book_id;
    }

    public void setId(int book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateOfWriting='" + dateOfWriting + '\'' +
                '}';
    }
}
