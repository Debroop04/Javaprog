import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private String operator;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "<html>This is a Calculator made by Debroop using Java.<br>It is implemented using java GUI.<br>Use commas between two numbers when using Permutations and Combinations</html>", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setBounds(100, 100, 400, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(10, 11, 360, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 30));  
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+",
            "←", "√", "x!","logₑ", // Backspace button
            "ⁿPᵣ","ⁿCᵣ","^",",",
            "sin","cos","tan","."
        };

        int xPos = 10;
        int yPos = 80;
        for (int i = 0; i < buttonLabels.length; i++) {
            String label = buttonLabels[i];
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 21));
            button.setBounds(xPos, yPos, 70, 70);

            // Setting background color and foreground (text) color
            if ("0123456789".contains(label)) {
                button.setBackground(new Color(200, 200, 255)); // Light blue for numbers
                button.setForeground(Color.BLACK);
            } else if (label.equals("C")) {
                button.setBackground(new Color(200, 0, 0)); // Red for 'C' (Clear)
                button.setForeground(Color.WHITE);
            } else if (label.equals("=")) {
                button.setBackground(new Color(0, 204, 0)); // Green for '=' (Equals)
                button.setForeground(Color.WHITE);
            } else if (label.equals("←")) {
                button.setBackground(new Color(255, 200, 100)); // Yellow for Backspace
                button.setForeground(Color.BLACK);
              
            }else if (label.equals("√")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);
            } 
               else if (label.equals("x!")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);
            }
               else if (label.equals("logₑ")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);
            }
               else if (label.equals("ⁿPᵣ")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);
            }
                else if (label.equals("ⁿCᵣ")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);          // button.setBounds(100, 50, 120, 40);
            }
                else if (label.equals("^")) {
                button.setBackground(new Color(0, 0, 153)); 
                button.setForeground(Color.WHITE);          // button.setBounds(100, 50, 120, 40);
            }
                else if (label.equals(",")) {
                button.setBackground(new Color(0, 0, 255)); 
                button.setForeground(Color.WHITE);         
            }
                 else if (label.equals("sin")) {
                button.setBackground(new Color(50, 0, 153)); 
                button.setForeground(Color.WHITE);         
            }
                else if (label.equals("cos")) {
                button.setBackground(new Color(50, 0, 153)); 
                button.setForeground(Color.WHITE);         
            }
                else if (label.equals("tan")) {
                button.setBackground(new Color(50, 0, 153)); 
                button.setForeground(Color.WHITE);         
            }
                  else if (label.equals(".")) {
                button.setBackground(new Color(0, 0, 255)); 
                button.setForeground(Color.WHITE);         
            }

                else {
                button.setBackground(new Color(100, 100, 255)); // Blue for operators
                button.setForeground(Color.WHITE);
            }

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(label);
                }
            });

            frame.getContentPane().add(button);

            xPos += 90;
            if ((i + 1) % 4 == 0) {
                xPos = 10;
                yPos += 90;
            }
        }
    }

    private void buttonClicked(String label) {
        if ("0123456789".contains(label)) {
            textField.setText(textField.getText() + label);
        } else if (label.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = null;
        } else if (label.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Cannot divide by zero");
                        result = 0;
                    }
                    break;
                case "^":
                    result =Math.pow(num1,num2);
                    break;
            }
            textField.setText(String.valueOf(result));
            operator = null;
        } else if (label.equals("←")) {  // Backspace logic
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));  // Remove the last character
            }
        }
           else if (label.equals("√")) {  // Square root logic
            double input = Double.parseDouble(textField.getText());
            if (input >= 0) {
                result = Math.sqrt(input);
                textField.setText(String.valueOf(result));
            } else {
                JOptionPane.showMessageDialog(frame, "Cannot calculate square root of a negative number");
            }
        }
             else if (label.equals("x!")) {  // Factorial logic
            long input = Long.parseLong(textField.getText());
            if (input < 0) {
                JOptionPane.showMessageDialog(frame, "Factorial is not defined for negative numbers");
            } else {
                result = factorial(input);
                textField.setText(String.valueOf(result));
            }
        }
          else if (label.equals("logₑ")) {  // Logarithm (natural log) logic
            double input = Double.parseDouble(textField.getText());
            if (input <= 0) {
                JOptionPane.showMessageDialog(frame, "Logarithm is only defined for positive numbers");
            } else {
                result = Math.log(input);  // Natural logarithm (log base e)
                textField.setText(String.valueOf(result));
            }
        }
             else if (label.equals("ⁿPᵣ")) {  // Permutation logic
            String[] inputs = textField.getText().split(",");
            if (inputs.length == 2) {
                long N = Long.parseLong(inputs[0]);
                long r = Long.parseLong(inputs[1]);
                if (N >= r && N >= 0 && r >= 0) {
                    result = permutation(N, r);
                    textField.setText(String.valueOf(result));
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Ensure n >= r and non-negative.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter two numbers separated by a comma (n,r).");
            }
        } 
            else if (label.equals("ⁿCᵣ")) {  // Combination logic
            String[] inputs = textField.getText().split(",");
            if (inputs.length == 2) {
                long N = Long.parseLong(inputs[0]);
                long r = Long.parseLong(inputs[1]);
                if (N >= r && N >= 0 && r >= 0) {
                    result = combination(N, r);
                    textField.setText(String.valueOf(result));
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Ensure n >= r and non-negative.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter two numbers separated by a comma (n,r).");
            }
        } 
           else if (label.equals(",")) {  
           textField.setText(textField.getText() + ","); 
        }
            else if (label.equals("sin")) {  
            double input = Double.parseDouble(textField.getText());
            double result = 0;
            result = Math.sin(Math.toRadians(input));
            textField.setText(String.valueOf(result));
        }
            else if (label.equals("cos")) {  
            double input = Double.parseDouble(textField.getText());
            double result = 0;
            result = Math.cos(Math.toRadians(input));
            textField.setText(String.valueOf(result));
        }
            else if (label.equals("tan")) {  
            double input = Double.parseDouble(textField.getText());
            double result = 0;
            result = Math.tan(Math.toRadians(input));
            textField.setText(String.valueOf(result));
        }
            else if (label.equals(".")) {  
           textField.setText(textField.getText() + "."); 
        }
           
            else {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                operator = label;
                textField.setText("");
            }
        }
    }

   private long factorial(long n) {
        long fact = 1;
        for (long i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
     private long permutation(long N, long r) {
        return factorial(N) / factorial(N - r);
    }
     private long combination(long n, long r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
     
}
