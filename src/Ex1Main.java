// Ex1Main.java
import java.util.Scanner;

public class Ex1Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers in the format <number>b<base>");

        while (true) {
            System.out.print("Enter a string as number#1 (or \"quit\" to end the program): ");
            String num1 = scanner.nextLine();

            if (num1.equalsIgnoreCase("quit")) {
                System.out.println("Quitting...");
                break;
            }

            System.out.print("Enter a string as number#2 (or \"quit\" to end the program): ");
            String num2 = scanner.nextLine();

            if (num2.equalsIgnoreCase("quit")) {
                System.out.println("Quitting...");
                break;
            }

            System.out.print("Enter a base for output (2-16 or A-G): ");
            String baseInput = scanner.nextLine();
            int base;
            try {
                base = baseInput.matches("[A-G]") ? 10 + (baseInput.charAt(0) - 'A') : Integer.parseInt(baseInput);
                if (base < 2 || base > 16) {
                    System.out.println("Invalid base. Try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid base input. Try again.");
                continue;
            }

            if (!Ex1.isValidNumber(num1) || !Ex1.isValidNumber(num2)) {
                System.out.println("One or both numbers are invalid. Try again.");
                continue;
            }

            String sum = Ex1.add(num1, num2, base);
            String product = Ex1.multiply(num1, num2, base);

            System.out.println(num1 + " + " + num2 + " = " + sum);
            System.out.println(num1 + " * " + num2 + " = " + product);
        }

        scanner.close();
    }
}
