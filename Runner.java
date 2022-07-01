package contacts;

import java.util.Scanner;

public class Runner {
    private final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook = new PhoneBook();

    public void runApp() {
        chooseAction();
    }

    private String readName() {
        System.out.println("Enter the name of the person:");
        return scanner.nextLine();
    }

    private String readSurname() {
        System.out.println("Enter the surname of the person:");
        return scanner.nextLine();
    }

    private String readNumber() {
        System.out.println("Enter the number:");
        return scanner.nextLine();
    }

    private void createContact() {
        System.out.println();
        String name = readName();
        String surname = readSurname();
        String number = readNumber();

        phoneBook.addContact(new Contact.ContactBuilder()
                .setName(name)
                .setSurname(surname)
                .setNumber(number)
                .build());
        System.out.println("The record added!");
    }

    private void menu() {
        System.out.println("Enter action (add, remove, edit, count, list, exit:");
    }

    private void chooseAction() {
        while (true) {
            menu();

            String userChoice = scanner.nextLine().toLowerCase().trim();

            switch (userChoice) {
                case "add":
                    createContact();
                    break;
                case "remove":
                    removeContact();
                    break;
                case "edit":
                    editContact();
                    break;
                case "count":
                    printContactsAmount();
                    break;
                case "list":
                    listContacts();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid action chosen!");
                    chooseAction();
                    break;
            }
        }
    }

    private void printContactsAmount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    private void listContacts() {
        if (phoneBook.getContacts().size() == 0) {
            System.out.println("No records in the list!");
            return;
        }
        for (Contact contact : phoneBook.getContacts()) {
            int index = phoneBook.getContacts().indexOf(contact) + 1;
            System.out.println(index + ". " + contact.toString());
        }
    }

    private void removeContact() {
        listContacts();
        if (phoneBook.getContacts().size() == 0) {
            return;
        }
        System.out.println("Select a record: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            boolean result = phoneBook.removeContact(index);
            if (result) System.out.println("The record removed!");
            else System.out.println("Invalid contact chosen!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter the index of the contact.");
        }
    }

    private void editContact() {
        listContacts();
        if (phoneBook.getContacts().size() == 0) {
            return;
        }
        System.out.println("Select a record: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            Contact contact = phoneBook.getContacts().get(index);
            System.out.println("Select a field (name, surname, number): ");
            String field = scanner.nextLine().toLowerCase();
            switch (field) {
                case "name":
                    contact.setName(readName());
                    System.out.println("The record updated!");
                    break;
                case "surname":
                    contact.setSurname(readSurname());
                    System.out.println("The record updated!");
                    break;
                case "number":
                    contact.setNumber(readNumber());
                    System.out.println("The record updated!");
                    break;
                default:
                    System.out.println("Invalid field chosen. Try again!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter the index of the contact.");
        }
    }

}

