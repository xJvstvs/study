import java.util.Scanner;

import static java.lang.System.exit;

public class Calc {
    private Scanner scanner;

    public Calc(){
        this.scanner = new Scanner(System.in);
    }

    private String getuserinput() {
        System.out.println("Enter your calculation");
        String input = scanner.nextLine();
        return input;
    }

    private String[] splitinput(String input) {
        String[] parts = input.split(" ");
        return parts;
    }

    private Float calculation(String[] parts) {
        float result = 0;
        try {
            result = Float.parseFloat(parts[0]);
            for (int i = 1; i < parts.length; i += 2) {
                String operator = parts[i];
                float operand = Float.parseFloat(parts[i + 1]);

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                    default:
                        System.out.println("Invalid operator: " + operator);
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            if (parts[0].equals("exit")) {
                System.out.println("Exiting the calculator.");
                exit(0);
            }
            System.out.println("Invalid input. Please enter a valid calculation.");
        }

        return result;
    }

    private void start() {
        String input = getuserinput();
        String[] parts = splitinput(input);
        String result = String.valueOf(calculation(parts));
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        Calc calc = new Calc();
        calc.start();
    }
}


