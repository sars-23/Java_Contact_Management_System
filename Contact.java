import java.util.Objects;

/** An immutable contact record used by the Swing application. */
public final class Contact {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String address;

    public Contact(String id, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = requireText(id, "ID");
        this.firstName = requireText(firstName, "First name");
        this.lastName = requireText(lastName, "Last name");
        this.email = validateEmail(email);
        this.phoneNumber = validatePhone(phoneNumber);
        this.address = address == null ? "" : address.trim();
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(fieldName + " is required.");
        return value.trim();
    }
    private static String validateEmail(String email) {
        String value = requireText(email, "Email");
        if (!value.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) throw new IllegalArgumentException("Enter a valid email address.");
        return value;
    }
    private static String validatePhone(String phoneNumber) {
        String value = requireText(phoneNumber, "Phone number");
        if (value.chars().filter(Character::isDigit).count() < 10) throw new IllegalArgumentException("Phone number must contain at least 10 digits.");
        return value;
    }
    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    @Override public boolean equals(Object other) { return other instanceof Contact && id.equals(((Contact) other).id); }
    @Override public int hashCode() { return Objects.hash(id); }
}
