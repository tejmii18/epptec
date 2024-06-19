package com.epptec.epptec;

import com.epptec.epptec.person.entity.Person;
import com.epptec.epptec.person.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class EpptecApplication {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EpptecApplication.class, args);
		PersonService personService = ctx.getBean(PersonService.class);

		boolean menuVisibility = true;

		while (menuVisibility) {
			System.out.print(getMenuMessage());

			switch (scanner.next()) {
				case "1":
					System.out.print("Zadej jméno: ");
					String name = scanner.next().trim();
					System.out.print("Zadej příjmení: ");
					String surname = scanner.next().trim();
					System.out.print("Zadej rodné číslo: ");
					String birthNumber = scanner.next().trim();
					Person person = new Person(name, surname, birthNumber);
					System.out.println(personService.addPerson(person));
					break;
				case "2":
					System.out.print("Zadej rodné číslo ke smazání: ");
					System.out.println(personService.removePerson(scanner.next().trim()));
					break;
				case "3":
					System.out.print("Zadej rodné číslo k vyhledání: ");
					System.out.println(personService.findPerson(scanner.next().trim()));;
					break;
				case "0":
					menuVisibility = false;
					break;
				default:
					System.out.println("Špatná volba");
					break;
			}
			if (menuVisibility) {
				menuVisibility = willContinue();
			}
		}
		ctx.close();

	}

	/**
	 * Metoda zobrazující menu
	 * @return menu
	 */
	public static String getMenuMessage() {
		return """
				   1 - Přidat osobu
				   2 - Odebrat osobu
				   3 - Vyhledat osobu podle rodného čísla
				   0 - Konec
				Vyber možnost: """;
	}

	/**
	 * Metoda pro pokračování po určité operaci
	 * @return true/false v závislosti na pokračování
	 */
	public static boolean willContinue() {
		System.out.print("Pokračovat? A/N: ");
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("a")) {
			return true;
		} else if (choice.equalsIgnoreCase("n")) {
			return false;
		} else {
			System.out.println("Špatná volba");
			willContinue();
		}
		return false;
    }

}

