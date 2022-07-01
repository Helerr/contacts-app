package contacts;

import java.util.ArrayList;

public class PhoneBook {
    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public boolean removeContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            contacts.remove(contact);
            return true;
        }
        return false;
    }



    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
