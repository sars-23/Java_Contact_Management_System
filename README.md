# Java Contact Management System - May 2025

A lightweight, feature-rich contact management application built with Java SE and Java Swing. This project demonstrates core Java concepts including encapsulation, data validation, efficient data structures, and GUI development.

## Features

### Core Features
- **Add Contacts**: Create new contacts with validation
- **View All Contacts**: Display all contacts in a table format
- **Search Contacts**: Search by name or email
- **Update Contacts**: Modify existing contact information
- **Delete Contacts**: Remove contacts from the system

### Technical Highlights
- **Encapsulated Data with Validation**: Strong input validation for all contact fields
- **Efficient CRUD Operations**: Uses HashMap for O(1) access to contacts by ID
- **No External Libraries**: Pure Java implementation using Java SE and Swing
- **Dynamic Table Management**: DefaultTableModel for real-time table updates
- **Dual Interface**: Both CLI and GUI interfaces for user interaction

## Project Structure

```
Java_Contact_Management_System/
├── Contact.java                 # Contact data model with validation
├── ContactManager.java          # Business logic for CRUD operations
├── ContactManagementCLI.java    # Command-line interface
├── ContactManagementGUI.java    # Graphical user interface
└── README.md                    # Project documentation
```

## Requirements

- Java SE 8 or higher
- No external dependencies required

## Getting Started

### Compiling

```bash
javac Contact.java
javac ContactManager.java
javac ContactManagementCLI.java
javac ContactManagementGUI.java
```

### Running CLI Application

```bash
java ContactManagementCLI
```

### Running GUI Application

```bash
java ContactManagementGUI
```

## Usage Guide

### CLI Interface

The CLI provides an interactive menu-driven interface:

1. **Add Contact**: Enter contact details (First Name, Last Name, Email, Phone, Address)
2. **View All Contacts**: Display all stored contacts
3. **Search Contact**: Search by name or email
4. **Update Contact**: Modify existing contact information
5. **Delete Contact**: Remove a contact from the system
6. **Exit**: Quit the application

### GUI Interface

The GUI provides a user-friendly graphical interface:

- **Input Fields**: Enter contact information at the top
- **Contact Table**: View and select contacts
- **Buttons**:
  - **Add**: Create a new contact
  - **Update**: Modify selected contact
  - **Delete**: Remove selected contact
  - **Search by ID**: Find contact by ID
  - **Clear Fields**: Reset all input fields
  - **Refresh**: Refresh the contact table

## Data Model

### Contact Class

The `Contact` class encapsulates contact information with validation:

- **ID**: Unique identifier for each contact
- **First Name**: Letters only, non-empty
- **Last Name**: Letters only, non-empty
- **Email**: Valid email format required
- **Phone Number**: Minimum 10 digits
- **Address**: Optional field

### Validation Rules

| Field | Rules |
|-------|-------|
| ID | Cannot be empty |
| First Name | Letters and spaces only, non-empty |
| Last Name | Letters and spaces only, non-empty |
| Email | Valid email format (contains @) |
| Phone | Minimum 10 digits |
| Address | Optional, defaults to "N/A" |

## Implementation Details

### ContactManager Class

Implements efficient CRUD operations using HashMap:

```
- addContact(): Create new contact with auto-generated ID
- getContact(id): Retrieve contact by ID - O(1) operation
- updateContact(id, ...): Modify contact details
- deleteContact(id): Remove contact - O(1) operation
- getAllContacts(): Retrieve all contacts
- searchByEmail(email): Search by email
- searchByName(name): Search by first or last name
- getTotalContacts(): Get total number of contacts
```

### GUI Components

The GUI uses Java Swing with DefaultTableModel:

- **DefaultTableModel**: Dynamically manages table data
- **JTable**: Displays contacts in a tabular format
- **Event Listeners**: Mouse and button click handlers
- **Input Validation**: Real-time validation with error messages
- **Responsive Design**: Resizable window with organized layout

## Tools Used

- **Java SE**: Core language and libraries
- **Java Swing**: GUI framework
- **HashMap**: Efficient data structure for contact storage
- **DefaultTableModel**: Dynamic table management
- **Git/GitHub**: Version control and repository management
- **Terminal**: Compilation and execution

## Example Workflow

### Adding a Contact (CLI)

```
Enter your choice: 1

--- Add New Contact ---
First Name: John
Last Name: Doe
Email: john.doe@example.com
Phone Number: 1234567890
Address: 123 Main Street

Contact added successfully with ID: 1
```

### Searching a Contact (CLI)

```
Enter your choice: 3

--- Search Contact ---
1. Search by Name
2. Search by Email
Choose search type: 2
Enter email: john.doe@example.com
ID: 1, Name: John Doe, Email: john.doe@example.com, Phone: 1234567890, Address: 123 Main Street
```

## Performance Characteristics

- **Add Contact**: O(1) average time
- **Get Contact by ID**: O(1) average time
- **Delete Contact**: O(1) average time
- **Search by Email/Name**: O(n) time (linear search)
- **Memory Usage**: O(n) where n is the number of contacts

## Future Enhancements

- File persistence (Save/Load contacts to file)
- Database integration
- Contact groups and categories
- Photo storage
- Birthday reminders
- Export to CSV/vCard format

## Author

Created: May 2025
