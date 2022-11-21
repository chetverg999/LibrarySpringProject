package ru.ivanov.lib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.lib.dao.BookDao;
import ru.ivanov.lib.dao.PersonDao;
import ru.ivanov.lib.models.Person;
import javax.validation.Valid;

/**
 * @author Ivanov Alexandr on 06.11.2022
 */

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PersonController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }


    @GetMapping("")  // список всех людей
    public String index(Model model) {
        model.addAttribute("personDaoModel", personDao.index());
        return "people/index";
    }


    @GetMapping("/new") // страница добавления нового человека
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, //процесс добавления нового человека с редиректом на весь список
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        personDao.edit(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}") // страница данного человека по его айди
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit") // страница изменения конкретного человека
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}") // процесс изменения данного человека с редиректом на весь список
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDao.update(id, person);
        return "redirect:/people";
    }


    @DeleteMapping("/{id}") // процесс удаления конкретного человека с редиректом на весь список
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }

}


