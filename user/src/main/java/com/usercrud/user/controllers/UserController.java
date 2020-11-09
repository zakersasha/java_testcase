package com.usercrud.user.controllers;

import com.usercrud.user.models.Person;
import com.usercrud.user.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());


    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/user")
    public String userMain(Model model) {
        Iterable<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        logger.info("Returning page with all created users");
        return "user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Person person) {
        logger.info("Returning user-add page");
        return "user-add";
    }

    @PostMapping("/user/add")
    public String userDataAdd(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("Incorrect user data. Return user-add page");
            return "user-add";
        }
        personRepository.save(person);
        logger.info("User was added to db. Redirect to all users");
        return "redirect:/user";

    }

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model) {
        if (!personRepository.existsById(id)) {
            logger.info("User wasn't found. Redirect to all users");
            return "redirect:/user";
        }

        Optional<Person> person = personRepository.findById(id);
        ArrayList<Person> result = new ArrayList<>();
        person.ifPresent(result::add);
        model.addAttribute("person", result);
        logger.info("Returning user-details form");
        return "user-details";
    }

    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if (!personRepository.existsById(id)) {
            logger.info("User wasn't found. Redirect to all users");
            return "redirect:/user";
        }

        logger.info("Returning user-edit form");
        Optional<Person> person = personRepository.findById(id);
        ArrayList<Person> result = new ArrayList<>();
        person.ifPresent(result::add);
        model.addAttribute("person", result);
        return "user-edit";
    }

    @PostMapping("/user/{id}/edit")
    public String userDataUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String birth_date, @RequestParam Integer age, @RequestParam Long phone_number, @RequestParam Boolean married, @Valid Person person, BindingResult bindingResult) {
        if (!personRepository.existsById(id)) {
            logger.info("User wasn't found. Redirect to all users");
            return "redirect:/user";
        }
        if (bindingResult.hasErrors()) {
            logger.info("Incorrect user data. Return user-edit page");
            return "user-edit";
        } else {
            person.setName(name);
            person.setSurname(surname);
            person.setEmail(email);
            person.setBirth_date(birth_date);
            person.setAge(age);
            person.setPhone_number(phone_number);
            person.setMarried(married);
            personRepository.save(person);
            logger.info("User data edited and saved. Redirect to all users");
            return "redirect:/user";
        }
    }

    @PostMapping("/user/{id}/remove")
    public String userDataRemove(@PathVariable(value = "id") long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
        logger.info("User was deleted. Redirect to all users");

        return "redirect:/user";
    }
}
