# Java Contact Management System

A desktop contact manager built with Java Swing. It is a small, self-contained project for practising object-oriented Java, validation, and event-driven GUI development.

## What it does

- Adds contacts with a generated numeric ID.
- Displays the current contacts in a non-editable table.
- Lets you select a row to load it into the form, then update or delete that contact.
- Looks up a contact by ID.
- Validates required names, email format, and phone numbers with at least 10 digits before saving.

Contacts are stored in a `LinkedHashMap` while the application is running. This provides lookup by ID and preserves insertion order in the table. The application has no file or database persistence: closing it discards all contacts.

## Requirements

- JDK 17 or later (the code uses only standard Java and Swing)

## Run locally

```bash
javac *.java
java ContactManagementGUI
```

To run the lightweight model tests without opening the GUI:

```bash
java -ea ContactManagerTest
```

## Project layout

```text
Contact.java                Contact data and validation
ContactManager.java         In-memory CRUD operations
ContactManagementGUI.java   Swing user interface
ContactManagerTest.java     JDK-only validation and CRUD tests
```

## Limitations and next steps

- Data is intentionally in memory only.
- Search is by ID only; there is no name or email search.
- The UI has no automated GUI test suite.

Useful next improvements would be persistence (for example, a local JSON file), a filter/search interface, and a more accessible keyboard-focused UI.
