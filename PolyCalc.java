import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class PolyCalc {
    private static boolean isDarkMode = false;
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Quadratic Equation Calculator");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Labels
        JLabel labelAA = new JLabel("Enter coefficients for the equation of the the form ax²+bx+c");
        labelAA.setBounds(40, 20, 350, 30);
        labelAA.setForeground(Color.GRAY);
        frame.add(labelAA);

        JLabel labelA = new JLabel("Enter a:");
        labelA.setBounds(50, 70, 50, 30);
        frame.add(labelA);

        JLabel labelB = new JLabel("Enter b:");
        labelB.setBounds(50, 110, 50, 30);
        frame.add(labelB);

        JLabel labelC = new JLabel("Enter c:");
        labelC.setBounds(50, 150, 50, 30);
        frame.add(labelC);

        // Input Fields
        JTextField textA = new JTextField();
        textA.setBounds(100, 70, 150, 30);
        frame.add(textA);

        JTextField textB = new JTextField();
        textB.setBounds(100, 110, 150, 30);
        frame.add(textB);

        JTextField textC = new JTextField();
        textC.setBounds(100, 150, 150, 30);
        frame.add(textC);

        // Solve Button
        JButton solveButton = new JButton("Solve");
        solveButton.setBounds(100, 190, 100, 40);
        frame.add(solveButton);

        JButton toggleButton = new JButton("☾");
        toggleButton.setBounds(10, 300, 70, 40);
        toggleButton.setToolTipText("Toggle between light/dark modes");
        frame.add(toggleButton);
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDarkMode = !isDarkMode; // Toggle mode
                updateMode(frame, labelAA, labelA, labelB, labelC, textA, textB, textC, solveButton, toggleButton);
            }
        });

        // Button Action
        solveButton.addActionListener((ActionEvent e) -> {
            try {
                double a = Double.parseDouble(textA.getText());
                double b = Double.parseDouble(textB.getText());
                double c = Double.parseDouble(textC.getText());
                solveQuadraticEquation(a, b, c);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Method to Solve Quadratic Equation
    private static void solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            JOptionPane.showMessageDialog(null, "This is not a quadratic equation (a ≠ 0).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double discriminant = b * b - 4 * a * c;
        String result;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            result = "Roots are real and distinct:\nRoot 1: " + root1 + "\nRoot 2: " + root2;
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            result = "Roots are real and equal:\nRoot: " + root;
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            result = "Roots are complex:\nRoot 1: " + realPart + " + " + imaginaryPart + "i" +
                    "\nRoot 2: " + realPart + " - " + imaginaryPart + "i";
        }
         String equation = formatEquation(a, b, c);

        JOptionPane.showMessageDialog(null, "Equation: " + equation + "\n" + result, "Solution", JOptionPane.INFORMATION_MESSAGE);
    }

      private static String formatEquation(double a, double b, double c) {
        return String.format("%.2fx² + %.2fx + %.2f = 0", a, b, c);
    }
       private static void updateMode(JFrame frame, JLabel labelAA,JLabel labelA,JLabel labelB,JLabel labelC, JTextField textA, JTextField textB, JTextField textC, JButton solveButton, JButton toggleButton) {
        if (isDarkMode) {
            frame.getContentPane().setBackground(Color.BLACK);
            labelAA.setForeground(Color.WHITE);
            labelA.setForeground(Color.WHITE);
            labelB.setForeground(Color.WHITE);
            labelC.setForeground(Color.WHITE);
            textA.setBackground(Color.DARK_GRAY);
            textA.setForeground(Color.WHITE);
            textB.setBackground(Color.DARK_GRAY);
            textB.setForeground(Color.WHITE);
            textC.setBackground(Color.DARK_GRAY);
            textC.setForeground(Color.WHITE);
            solveButton.setBackground(Color.GRAY);
            solveButton.setForeground(Color.WHITE);
            toggleButton.setBackground(Color.DARK_GRAY);
            toggleButton.setForeground(Color.WHITE);
            toggleButton.setText("☀︎");
        } else {
            frame.getContentPane().setBackground(Color.WHITE);
            labelAA.setForeground(Color.GRAY);
            labelA.setForeground(Color.BLACK);
            labelB.setForeground(Color.BLACK);
            labelC.setForeground(Color.BLACK);
            textA.setBackground(Color.WHITE);
            textA.setForeground(Color.BLACK);
            textB.setBackground(Color.WHITE);
            textB.setForeground(Color.BLACK);
            textC.setBackground(Color.WHITE);
            textC.setForeground(Color.BLACK);
            solveButton.setBackground(Color.LIGHT_GRAY);
            solveButton.setForeground(Color.BLACK);
            toggleButton.setBackground(Color.WHITE);
            toggleButton.setForeground(Color.BLACK);
            toggleButton.setText("☾");
        }
    }
}