package ru.ivanov.lib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.lib.dao.BookDao;
import ru.ivanov.lib.dao.PersonDao;
import ru.ivanov.lib.models.Book;
import ru.ivanov.lib.models.Person;
import javax.validation.Valid;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

@Controller
@RequestMapping("/books")
public class BookController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public BookController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookDaoModel", bookDao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDao.edit(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.show(id));
        model.addAttribute("bookPerson", personDao.showPerson(id));
        model.addAttribute("people", personDao.index());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/deletePerson")
    public String deletePerson(@ModelAttribute("book") Book book,
                         @PathVariable("id") int id) {
        bookDao.deletePerson(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/addPerson")
    public String addPerson(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person,
                               @PathVariable("id") int id) {
        bookDao.addPerson(person.getId(), id);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

}
