/** Lightweight tests that can run with the JDK alone. */
public final class ContactManagerTest {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Contact saved = manager.addContact("Ada", "Lovelace", "ada@example.com", "+1 555 123 4567", "London");
        assertEquals("1", saved.getId(), "new contacts receive sequential IDs");
        manager.updateContact("1", "Ada", "Byron", "ada@example.com", "+1 555 123 4567", "London");
        assertEquals("Byron", manager.getContact("1").getLastName(), "updates replace the contact");
        manager.deleteContact("1");
        assertEquals(0, manager.getAllContacts().size(), "deleting removes the contact");
        assertThrows(() -> new Contact("1", "Ada", "Lovelace", "invalid", "1234567890", ""));
        assertThrows(() -> new Contact("1", "Ada", "Lovelace", "ada@example.com", "123", ""));
        System.out.println("ContactManagerTest passed");
    }
    private static void assertThrows(Runnable action) { try { action.run(); throw new AssertionError("Expected IllegalArgumentException"); } catch (IllegalArgumentException expected) { } }
    private static void assertEquals(Object expected, Object actual, String message) { if (!expected.equals(actual)) throw new AssertionError(message + ": expected " + expected + ", got " + actual); }
}
