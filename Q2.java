import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class registration {
    private String name;
    private String email;
    private String  age;

    public registration(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getemail() {
        return email;
    }

    public String getage() {
        return age;
    }

    public void setName(String name2) {


    }

    public void setemail(String email2) {


    }

    public void setage(String age2) {


    }
}
public class Q2 {
    private ArrayList<registration> a = new ArrayList<>();
    private DefaultListModel<String> b = new DefaultListModel<>();
    private JList<String> d;

    public Q2() {
        JFrame frame = new JFrame("registration form");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        d = new JList<>(b);
        frame.add(new JScrollPane(d), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.NORTH);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                information();
            }
        });



        buttonPanel.add(addButton);


        frame.setVisible(true);
    }
    private void information() {
        String name = JOptionPane.showInputDialog("Enter the Name:");
        if (name != null && !name.isEmpty()) {
            String email = JOptionPane.showInputDialog("Enter the email:");
            if (email != null && !email.isEmpty()) {
                String age = JOptionPane.showInputDialog("Enter the age:");
                if (age != null && !age.isEmpty()) {

                    registration c1 = new registration(name, email, age);
                    a.add(c1);
                    b.addElement(c1.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Age field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Name field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Q2();
            }
        });
    }
}
