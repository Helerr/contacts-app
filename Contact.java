package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String name;
    private String surname;
    private String number;
    private static final Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");

    private Contact(ContactBuilder contact) {
        this.name = contact.name;
        this.surname = contact.surname;
        this.number = contact.number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        if (this.number.isEmpty()) {
            return "[no number]";
        } else {
            return number;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber(String number) {
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            this.number = number;
        } else {
            this.number = "";
            System.out.println("Wrong number format!");
        }
    }

    public boolean hasNumber() {
        return this.number.isEmpty();
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + ", " + this.getNumber();
    }

    public static class ContactBuilder {
        private String name;
        private String surname;
        private String number;


        ContactBuilder setName(String name) {
            this.name = name;
            return this;
        }

        ContactBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        ContactBuilder setNumber(String number) {
            Matcher matcher = pattern.matcher(number);
            if (matcher.matches()) {
                this.number = number;
            } else {
                this.number = "";
                System.out.println("Wrong number format!");
            }
            return this;
        }

        Contact build() {
            return new Contact(this);
        }
    }

}
