import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class q3 {
    private JFrame A;
    private JPanel B;
    private JTextField d;

    private String INPUT = "";
    private double r = 0;
    private char o = ' ';

    public q3() {
        A = new JFrame("Basic Calculator");
        A.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        A.setSize(700, 600);
        A.setLayout(new BorderLayout());

        d = new JTextField();
        d.setEditable(false);
        A.add(d, BorderLayout.CENTER);

        B = new JPanel();
        B.setLayout(new GridLayout(4, 4));

        String[] number = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : number) {
            JButton button = new JButton(label);
            button.addActionListener(new CalculatorButtonListener());
            B.add(button);
        }

        A.add(B, BorderLayout.CENTER);
        A.setVisible(true);
    }

    private class CalculatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String c= e.getActionCommand();
            if (Character.isDigit(c.charAt(0)) || c.equals(".")) {
                INPUT += c;
                d.setText(INPUT);
            } else if (c.equals("=")) {
                calculate();
            } else if (c.length() == 1) {
                if (INPUT.isEmpty()) {
                    INPUT = "0";
                }
                o = c.charAt(0);
                r = Double.parseDouble(INPUT);
                INPUT= "";
            }
        }

        private void calculate() {
            if (!INPUT.isEmpty()) {
                double result= Double.parseDouble(INPUT);
                switch (o) {
                    case '+':
                        r += result;
                        break;
                    case '-':
                        r -= result;
                        break;
                    case '*':
                        r *= result;
                        break;
                    case '/':
                        if (result != 0) {
                            r /= result;
                        } else {
                            d.setText("Error");
                            return;
                        }
                        break;
                }
                d.setText(String.valueOf(r));
                INPUT = "";
                o = ' ';
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new q3();
            }
        });
    }
}


