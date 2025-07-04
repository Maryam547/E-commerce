package com.fawry.ecommerce.utils;

import java.util.Scanner;


public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    public static double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    public static int getChoice(String prompt, int min, int max) {
        while (true) {
            int choice = getInt(prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.println("Invalid choice! Please enter a number between " + min + " and " + max);
        }
    }
    
    public static boolean getConfirmation(String prompt) {
        while (true) {
            String input = getString(prompt + " (y/n): ").toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' for yes or 'n' for no.");
        }
    }
    
    public static void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
