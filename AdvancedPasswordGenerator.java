import javax.swing.*; // For GUI components
import java.awt.*; // For layout and color
import java.util.List; // For List interface
import java.util.ArrayList; // For ArrayList implementation
import java.util.Random; // For random character generation
import java.util.Collections; // For shuffling characters

// Main class for the advanced password generator GUI application
public class AdvancedPasswordGenerator {

    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Password Generator");
        frame.setSize(450, 300); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app when window closes
        frame.setLayout(new GridLayout(7, 2, 10, 10)); // Grid layout with 7 rows, 2 columns
        frame.getContentPane().setBackground(Color.BLACK); // Set background color of window

        // Set a monospace font for consistent character width
        Font font = new Font("Consolas", Font.PLAIN, 14);

        // Create input and output fields
        JTextField lengthField = new JTextField(); // Input field for password length
        JCheckBox upperCaseBox = new JCheckBox("Include Uppercase"); // Option to include uppercase letters
        JCheckBox lowerCaseBox = new JCheckBox("Include Lowercase"); // Option to include lowercase letters
        JCheckBox numbersBox = new JCheckBox("Include Numbers"); // Option to include numbers
        JCheckBox symbolsBox = new JCheckBox("Include Special Characters"); // Option to include symbols
        JButton generateButton = new JButton("Generate Password"); // Button to trigger password generation
        JTextField resultField = new JTextField(); // Field to display generated password
        resultField.setEditable(false); // Make result field read-only

        // Apply consistent dark styling to components
        Component[] components = {
            lengthField, upperCaseBox, lowerCaseBox, numbersBox, symbolsBox, resultField, generateButton
        };
        for (Component comp : components) {
            comp.setBackground(Color.DARK_GRAY);
            comp.setForeground(Color.GREEN);
            comp.setFont(font);
        }

        // Create and style label for password length
        JLabel lengthLabel = new JLabel("Password Length (min 4):");
        lengthLabel.setForeground(Color.GREEN);
        lengthLabel.setFont(font);

        // Add all components to the frame
        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(upperCaseBox);
        frame.add(lowerCaseBox);
        frame.add(numbersBox);
        frame.add(symbolsBox);
        frame.add(generateButton);
        frame.add(resultField);

        // Define behavior when "Generate Password" button is clicked
        generateButton.addActionListener(e -> {
            try {
                int length = Integer.parseInt(lengthField.getText()); // Read user input for password length

                // Validate minimum length
                if (length < 4) {
                    resultField.setText("Length must be at least 4.");
                    return;
                }

                // Get checkbox selections
                boolean useUpper = upperCaseBox.isSelected();
                boolean useLower = lowerCaseBox.isSelected();
                boolean useNumbers = numbersBox.isSelected();
                boolean useSymbols = symbolsBox.isSelected();

                // Ensure at least one character type is selected
                if (!useUpper && !useLower && !useNumbers && !useSymbols) {
                    resultField.setText("Select at least one character type.");
                    return;
                }

                // Generate password using selected options
                String password = generatePassword(length, useUpper, useLower, useNumbers, useSymbols);
                resultField.setText(password); // Display result

            } catch (NumberFormatException ex) {
                // Handle invalid number input
                resultField.setText("Enter a valid number.");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to generate password based on user-selected criteria
    public static String generatePassword(int length, boolean useUpper, boolean useLower, boolean useNumbers, boolean useSymbols) {
        // Define possible character sets
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*()-_=+[]{};:,.<>?/";

        StringBuilder charPool = new StringBuilder(); // Pool of all allowed characters
        List<Character> mandatoryChars = new ArrayList<>(); // Ensure at least one of each selected type

        Random random = new Random(); // Random number generator

        // Add one random character from each selected set to guarantee inclusion
        if (useUpper) {
            charPool.append(upperCase);
            mandatoryChars.add(upperCase.charAt(random.nextInt(upperCase.length())));
        }
        if (useLower) {
            charPool.append(lowerCase);
            mandatoryChars.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
        }
        if (useNumbers) {
            charPool.append(numbers);
            mandatoryChars.add(numbers.charAt(random.nextInt(numbers.length())));
        }
        if (useSymbols) {
            charPool.append(special);
            mandatoryChars.add(special.charAt(random.nextInt(special.length())));
        }

        // Fill remaining characters randomly from the full character pool
        List<Character> passwordChars = new ArrayList<>(mandatoryChars); // Start with mandatory characters
        for (int i = mandatoryChars.size(); i < length; i++) {
            passwordChars.add(charPool.charAt(random.nextInt(charPool.length())));
        }

        // Shuffle to prevent predictable character order
        Collections.shuffle(passwordChars);

        // Convert character list to final password string
        StringBuilder finalPassword = new StringBuilder();
        for (char ch : passwordChars) {
            finalPassword.append(ch);
        }

        return finalPassword.toString(); // Return final result
    }
}
