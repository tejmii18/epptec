package com.epptec.epptec.person;

import java.util.List;

public class PersonValidator {

    // Upravené zadané rodné číslo - buďto s a nebo bez lomítka
    private String modifiedBirthNumber = "";

    // Nalezená osoba pro smazání/výpis detailu
    private Person foundPerson;

    /**
     * Validace osoby pro přidání
     * @param person - Osoba
     * @param persons - Databáze osob
     * @return true v závislosti, zda je osoba validní
     * @throws Exception v případě nevalidnosti
     */
    public boolean isPersonValid(Person person, List<Person> persons) throws Exception {
        if (!(validateBirthNumber(person.getBirthNumber())
                && validateNameAndSurname(person))) {
            throw new Exception("Osobu nelze přidat! Nevalidní údaje!");
        }
        if (isInList(persons, person.getBirthNumber())) {
            throw new Exception("Osobu nelze přidat! Již existuje!");
        }
        return true;
    }

    /**
     * Validace osoby pro smazání
     * @param birthNumber - Rodné číslo
     * @param persons - Databáze osob
     * @return nalezenou shodu pro smazání
     * @throws Exception v případě neexistence osoby
     */
    public Person deletePerson(String birthNumber, List<Person> persons) throws Exception {
        if (validateBirthNumber(birthNumber) && isInList(persons, birthNumber)) {
           return foundPerson;
        } else {
            throw new Exception("Osobu nelze vymazat! V seznamu neexistuje!");
        }
    }

    /**
     * Validace osoby pro zobrazení detailu
     * @param birthNumber - Rodné číslo
     * @param persons - Databáze osob
     * @return nalezenou shodu pro detail
     * @throws Exception v případě neexistence osoby
     */
    public Person findPerson(String birthNumber, List<Person> persons) throws Exception {
        if (validateBirthNumber(birthNumber) && isInList(persons, birthNumber)) {
            return foundPerson;
        } else {
            throw new Exception("Osoba v seznamu neexistuje");
        }
    }

    /**
     * Validace jména a příjmení
     * @param person - Osoba
     * @return true/false v závislosti na validaci
     */
    private boolean validateNameAndSurname(Person person) {
        if (person.getName() == null || person.getSurname() == null) {
            return false;
        }
        return true;
    }

    /**
     * Validace rodného čísla.
     * Také nastavuje modifikované rodné číslo (modifiedBirthNumber) - s či bez lomítka
     * @param birthNumber - Rodné číslo
     * @return true/false v závislosti na validaci
     */
    private boolean validateBirthNumber(String birthNumber) {
        if (birthNumber.length() == 10) {
            if (isOnlyDigits(birthNumber)) {
                modifiedBirthNumber = birthNumber.substring(0, 6) + "/" + birthNumber.substring(6);
                return true;
            }
        } else if (birthNumber.length() == 11) {
            String firstPart = birthNumber.substring(0, 6);
            String secondPart = birthNumber.substring(6, 7);
            String thirdPart = birthNumber.substring(7, 11);
            if (isOnlyDigits(firstPart) && isOnlyDigits(thirdPart) && secondPart.equals("/")) {
                modifiedBirthNumber = firstPart + thirdPart;
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda pro zjištění, zda je osoba v listu.
     * Také nastavuje foundPerson na nalezenou osobu.
     * @param persons - Databáze osob
     * @param birthNumber - Rodné číslo
     * @return true/false pokud v listu existuije/eneexistuje
     */
    private boolean isInList(List<Person> persons, String birthNumber) {
        for (Person person : persons) {
            if (person.getBirthNumber().equals(birthNumber) || person.getBirthNumber().equals(modifiedBirthNumber)) {
                foundPerson = person;
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda pro zjištění, zda String je celočíselný
     * @param digits - část nebo celé rodné číslo
     * @return true/false pokud je string číslo
     */
    private boolean isOnlyDigits(String digits) {
        return digits.matches("\\d+");
    }
}
