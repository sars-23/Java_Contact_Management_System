import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ContactManagementGUI extends JFrame {
    private ContactManager manager;
    private DefaultTableModel tableModel;
    private JTable contactTable;
    private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtAddress, txtSearchId;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnClear, btnRefresh;

    public ContactManagementGUI() {
        this.manager = new ContactManager();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Java Contact Management System - May 2025");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top Panel - Input Fields
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Center Panel - Table
        JPanel tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Bottom Panel - Buttons
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Contact Information"));

        panel.add(new JLabel("First Name:"));
        txtFirstName = new JTextField();
        panel.add(txtFirstName);

        panel.add(new JLabel("Last Name:"));
        txtLastName = new JTextField();
        panel.add(txtLastName);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Phone Number:"));
        txtPhone = new JTextField();
        panel.add(txtPhone);

        panel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        panel.add(txtAddress);

        panel.add(new JLabel("Search ID:"));
        txtSearchId = new JTextField();
        panel.add(txtSearchId);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Contact List"));

        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        contactTable = new JTable(tableModel);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = contactTable.getSelectedRow();
                if (row >= 0) {
                    loadContactToFields(row);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(contactTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6, 10, 10));

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addContact());
        panel.add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateContact());
        panel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteContact());
        panel.add(btnDelete);

        btnSearch = new JButton("Search by ID");
        btnSearch.addActionListener(e -> searchContact());
        panel.add(btnSearch);

        btnClear = new JButton("Clear Fields");
        btnClear.addActionListener(e -> clearFields());
        panel.add(btnClear);

        btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshTable());
        panel.add(btnRefresh);

        return panel;
    }

    private void addContact() {
        try {
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            manager.addContact(firstName, lastName, email, phone, address);
            JOptionPane.showMessageDialog(this, "Contact added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateContact() {
        try {
            int row = contactTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Please select a contact to update.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = (String) tableModel.getValueAt(row, 0);
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            manager.updateContact(id, firstName, lastName, email, phone, address);
            JOptionPane.showMessageDialog(this, "Contact updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteContact() {
        try {
            int row = contactTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = (String) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                manager.deleteContact(id);
                JOptionPane.showMessageDialog(this, "Contact deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTable();
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchContact() {
        try {
            String id = txtSearchId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a contact ID.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Contact contact = manager.getContact(id);
            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtEmail.setText(contact.getEmail());
            txtPhone.setText(contact.getPhoneNumber());
            txtAddress.setText(contact.getAddress());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadContactToFields(int row) {
        String firstName = (String) tableModel.getValueAt(row, 1);
        String lastName = (String) tableModel.getValueAt(row, 2);
        String email = (String) tableModel.getValueAt(row, 3);
        String phone = (String) tableModel.getValueAt(row, 4);
        String address = (String) tableModel.getValueAt(row, 5);

        txtFirstName.setText(firstName);
        txtLastName.setText(lastName);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Contact> contacts = manager.getAllContacts();
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{
                    contact.getId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhoneNumber(),
                    contact.getAddress()
            });
        }
    }

    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtSearchId.setText("");
        contactTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactManagementGUI());
    }
}