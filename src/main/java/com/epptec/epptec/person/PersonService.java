package com.epptec.epptec.person;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    // Validátory
    public static PersonValidator personValidator = new PersonValidator();

    // CRUD akce
    public final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Přidávání osoby
     * @param person - Osoba
     * @return ok/error v případě přidání/nepřidání osoby
     */
    public String addPerson(Person person) {
        try {
            if (personValidator.isPersonValid(person, (List<Person>) personRepository.findAll())) {
                personRepository.save(person);
                return "ok";
            } else {
                return "nok";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Mazání osoby
     * @param birthNumber - Rodné číslo
     * @return ok/error v případě odebrání/neodebrání osoby
     */
    public String removePerson(String birthNumber) {
        try {
            personRepository.delete(personValidator.deletePerson(birthNumber,  (List<Person>) personRepository.findAll()));
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Hledání osoby
     * @param birthNumber - Rodné číslo
     * @return detail osoby/error v případě nalezení/nenalezení osoby
     */
    public String findPerson(String birthNumber) {
        try {
            return personValidator.findPerson(birthNumber,  (List<Person>) personRepository.findAll()).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Nalezení všech osob v DB
     * @return všechny osoby/null
     */
    public Iterable<Person> findAll() {
        try {
            return personRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
