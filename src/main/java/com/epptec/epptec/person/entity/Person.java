package com.epptec.epptec.person.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.Period;

@Entity(name = "PERSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "BIRTHNUMBER", nullable = false)
    private String birthNumber;


    public Person(String name, String surname, String birthNumber) {
        this.name = name;
        this.surname = surname;
        this.birthNumber = birthNumber;
    }

    /**
     * Metoda pro vypočítání věku.
     * Aktálně je nastavená tak, že, pokud rodné číslo začíná 00-23 včetně, jedná se o ročník 2000+
     * Pokud začíná 24+ jedná se o ročník 1900. Tudíž osoba nemůže být starší než 100 let včetně.
     * @return věk
     */
    public int getAge() {
        int year = Integer.parseInt(birthNumber.substring(0,2)) < 24 ? Integer.parseInt(birthNumber.substring(0,2)) + 2000
                : Integer.parseInt(birthNumber.substring(0,2)) + 1900;
        int month = Integer.parseInt(birthNumber.substring(2,4)) > 12 ? (Integer.parseInt(birthNumber.substring(2,4)) - 50)
                : Integer.parseInt(birthNumber.substring(2,4));
        int day = Integer.parseInt(birthNumber.substring(4,6));

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    @Override
    public String toString() {
        return  "Jméno: " + name + '\n' +
                "Příjmení: " + surname + '\n' +
                "Věk: " + getAge() + '\n' +
                "Rodné číslo:" + birthNumber;
    }

}
