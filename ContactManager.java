import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Stores contacts in memory for the lifetime of the application. */
public final class ContactManager {
    private final Map<String, Contact> contacts = new LinkedHashMap<>();
    private int nextId = 1;
    public Contact addContact(String firstName, String lastName, String email, String phoneNumber, String address) {
        String id = String.valueOf(nextId++);
        Contact contact = new Contact(id, firstName, lastName, email, phoneNumber, address);
        contacts.put(id, contact);
        return contact;
    }
    public Contact getContact(String id) {
        Contact contact = contacts.get(id);
        if (contact == null) throw new IllegalArgumentException("No contact was found with ID " + id + ".");
        return contact;
    }
    public void updateContact(String id, String firstName, String lastName, String email, String phoneNumber, String address) {
        getContact(id);
        contacts.put(id, new Contact(id, firstName, lastName, email, phoneNumber, address));
    }
    public void deleteContact(String id) { getContact(id); contacts.remove(id); }
    public List<Contact> getAllContacts() { return new ArrayList<>(contacts.values()); }
}
