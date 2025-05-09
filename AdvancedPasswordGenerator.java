//import all the necessary classes
import java.security.SecureRandom;//This package is use to generate random numbers
import java.util.ArrayList;//This is used for dynamic listing
import java.util.List;
import java.util.Scanner;//This is used for user input

public class AdvancedPasswordGenerator {
    
    //Constant character set for different characters
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_+=<>?/";

    public static void main(String[] args) {

        //create object for generating random values and for user input
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("Welcome to Secure Password Generator!\n");

        // Step 1: Ask the user for valid password length
        int length = 0;
        while (length < 4) {
            System.out.print("Enter desired password length (min 4): ");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();//Read user input
                scanner.nextLine(); // Clear newline from buffer
                if (length < 4) {
                    System.out.println("⚠️ Password length should be at least 4.");
                }
            } else {
                System.out.println("⚠️ Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Step 2: Ask the user which character type they want to use or include
        boolean useUpper = askChoice(scanner, "Include uppercase letters? (y/n): ");
        boolean useLower = askChoice(scanner, "Include lowercase letters? (y/n): ");
        boolean useDigits = askChoice(scanner, "Include numbers? (y/n): ");
        boolean useSymbols = askChoice(scanner, "Include special characters? (y/n): ");

        // Step 3: Build selected character pool on the basis of user choice
        StringBuilder charPool = new StringBuilder(); //All eligible characters
        List<Character> mandatoryChars = new ArrayList<>(); //At least one from  each chosen type

        if (useUpper) {
            charPool.append(UPPERCASE);
            mandatoryChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (useLower) {
            charPool.append(LOWERCASE);
            mandatoryChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (useDigits) {
            charPool.append(DIGITS);
            mandatoryChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        if (useSymbols) {
            charPool.append(SYMBOLS);
            mandatoryChars.add(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        //if no characters are selected close the program
        if (charPool.length() == 0) {
            System.out.println("❌ You must select at least one character type.");
            return;
        }

        // Step 4: Start building the password
        //First include one character from each selected type 
        List<Character> passwordChars = new ArrayList<>(mandatoryChars);
        //Fill the remaining character randomly from the total pool
        for (int i = mandatoryChars.size(); i < length; i++) {
            passwordChars.add(charPool.charAt(random.nextInt(charPool.length())));
        }

        // Step 5: Shuffle the password so that mandatory characters are not always at the start
        StringBuilder finalPassword = new StringBuilder();
        while (!passwordChars.isEmpty()) {
            int index = random.nextInt(passwordChars.size());
            finalPassword.append(passwordChars.remove(index));
        }

        // Step 6:  Generate the Output
        System.out.println("\nGenerated Password: " + finalPassword);
        scanner.close();//scanner close
    }

    // Helper method to get yes/no input
    //returns true for y and false for n
    private static boolean askChoice(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            else if (input.equals("n")) return false;
            else System.out.println("⚠️ Please enter 'y' or 'n'.");
        }
    }
}
