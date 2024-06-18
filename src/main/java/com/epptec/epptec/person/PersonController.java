package com.epptec.epptec.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List getPersons() {
        return (List) personService.findAll();
    }

    @PostMapping("/add")
    public String addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @GetMapping("/find/{birthNumber}")
    public String getPerson(@PathVariable String birthNumber) {
        return personService.findPerson(birthNumber);
    }

    @PostMapping("/remove/{birthNumber}")
    public String removePerson(@PathVariable String birthNumber) {
        return personService.removePerson(birthNumber);
    }
}
