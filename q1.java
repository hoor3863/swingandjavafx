import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class phonebook {
    private String name;
    private String number;
    private String address;

    public phonebook(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String number() {
        return number;
    }

    public String address() {
        return address;
    }

    public void name(String name) {
    }
    public void number(String number) {
    }
    public void address(String address) {
    }
}

public class q1 {
    private ArrayList<phonebook> contacts = new ArrayList<>();
    private DefaultListModel<String> contactListModel = new DefaultListModel<>();
    private JList<String> contactList;

    public q1() {
        JFrame frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        contactList = new JList<>(contactListModel);
        frame.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addContact();
            }
        });

        JButton editButton = new JButton("Edit Contact");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

        frame.setVisible(true);
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog("Enter Name:");
        if (name != null) {
            String number = JOptionPane.showInputDialog("Enter Phone Number:");
            String address = JOptionPane.showInputDialog("Enter Address:");
            phonebook contact = new phonebook(name, number, address);
            contacts.add(contact);
            contactListModel.addElement(contact.getName());
        }
    }

    private void editContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            String name = JOptionPane.showInputDialog("Edit Name:", contacts.get(selectedIndex).getName());
            if (name != null) {
                String number = JOptionPane.showInputDialog("Edit Phone Number:", contacts.get(selectedIndex).number());
                String address = JOptionPane.showInputDialog("Edit Address:", contacts.get(selectedIndex).address());
                phonebook a = contacts.get(selectedIndex);
                a.name(name);
                a.number(number);
                a.address(address);
                contactListModel.setElementAt(a.getName(), selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a contact to edit.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new q1();
            }
        });
    }
}

