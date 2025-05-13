// Importing necessary classes
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // A map to store static currency conversion rates relative to USD
    private static final Map<String, Double> conversionRates = new HashMap<>();

    // Static block to initialize the currency rates
    static {
        // 1 USD is assumed to be equal to:
        conversionRates.put("USD", 1.0);     // US Dollar
        conversionRates.put("INR", 83.0);    // Indian Rupee
        conversionRates.put("EUR", 0.93);    // Euro
        conversionRates.put("GBP", 0.80);    // British Pound
        conversionRates.put("JPY", 155.2);   // Japanese Yen
    }

    public static void main(String[] args) {
        // Scanner object to take user input from the console
        Scanner scanner = new Scanner(System.in);
        String choice = " "; // To hold user decision for repeating conversion

        // Welcome message and available currency options
        System.out.println("====== Currency Converter ======");
        System.out.println("Available Currencies: USD, INR, EUR, GBP, JPY");

        // Loop continues until the user decides to exit
        do {
            // Ask for source currency
            System.out.print("\nEnter source currency (e.g., USD): ");
            String from = scanner.nextLine().toUpperCase(); // Normalize input to uppercase

            // Ask for target currency
            System.out.print("Enter target currency (e.g., INR): ");
            String to = scanner.nextLine().toUpperCase(); // Normalize input to uppercase

            // Validate if entered currencies are supported
            if (!conversionRates.containsKey(from) || !conversionRates.containsKey(to)) {
                System.out.println("❌ Invalid currency code. Please try again.");
                continue; // Restart the loop
            }

            // Ask for the amount to convert
            System.out.print("Enter amount to convert: ");
            double amount;

            try {
                // Try to parse the amount to a number
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Catch error if input is not a valid number
                System.out.println("❌ Invalid amount. Please enter a numeric value.");
                continue; // Restart the loop
            }

            // Conversion Logic:
            // 1. Convert from source currency to USD
            double inUSD = amount / conversionRates.get(from);

            // 2. Convert from USD to target currency
            double converted = inUSD * conversionRates.get(to);

            // Print the converted amount
            System.out.printf("✅ Converted Amount: %.2f %s%n", converted, to);

            // Ask if the user wants to perform another conversion
            System.out.print("\nDo you want to convert another amount? (yes/no): ");
            choice = scanner.nextLine().toLowerCase(); // Normalize input to lowercase

        } while (choice.equals("yes") || choice.equals("y")); // Loop if user says "yes" or "y"

        // Exit message
        System.out.println("👋 Thank you for using the Currency Converter!");
        scanner.close(); // Close scanner to release system resources
    }
}
