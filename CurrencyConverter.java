//Import the Scanner class for user input
import java.util.*;
public class CurrencyConverter {
          public static void main(String[]args)
          {
            Scanner sc=new Scanner(System.in);//Create a object for user input
            //Welcome message and Currency Conversion Operation for User
            System.out.println("Welcome to Currency Converter");
            System.out.println("Availiable Currencies");
            System.out.println("1.USD to INR");
            System.out.println("2.INR to USD");
            System.out.println("3.EUR to INR");
            System.out.println("4.INR to EUR");
            int choice=sc.nextInt();//User Input
            double convertedamt,amt;
            //Switch case to handle different conversions
            switch(choice)
            {
                case 1:
                //USD to INR Conversion
                System.out.println("Enter amount in USD");
                amt=sc.nextDouble();
                convertedamt=amt*83.00;
                System.out.println("Amount in INR : ₹"+convertedamt);
                break;
                
                case 2:
                //INR to USD Conversion
                System.out.println("Enter amount in INR");
                amt=sc.nextDouble();
                convertedamt=amt/83.00;
                System.out.println("Amount in USD : $"+convertedamt);
                break;
                
                case 3:
                //EUR to INR Conversion
                System.out.println(":Enter amount in EUR");
                amt=sc.nextDouble();
                convertedamt=amt*89.50;
                System.out.println("Amaount in INR : ₹"+convertedamt);
                break;
                
                case 4:
                //INR to EUR Conversion
                System.out.println("Enter amount in INR");
                amt=sc.nextDouble();
                convertedamt=amt/89.50;
                System.out.println("Amount in EUR : €"+convertedamt);
                 
                default:
                //Default choice if the user input is invalid
                System.out.println("Invalid choice try again");
            }
            sc.close();//Scanner close
          }
}
