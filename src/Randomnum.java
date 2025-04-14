import java.util.Scanner;

public class Randomnum {
    public static Integer number;
    public static Integer rndmnum;
    public static Integer i = 0;
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number between 1 and 100:");
        String input = scanner.nextLine();
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            input();
        }
    }

    public static void generate() {
        rndmnum = (int)(Math.random() * 100) + 1;
        System.out.println("Random number generated: " + rndmnum);
    }

    public static void equals(){
        if (number < 1 || number > 100) {
            System.out.println("The number is out of range. Please enter a number between 1 and 100.");
            input();
        }
        while (number != rndmnum) {
            if (number > rndmnum) {
                System.out.println("The numbers is greater than the random number.");
                i++;
                input();
                equals();
            } else {
                System.out.println("The number is less than the random number.");
                i++;
                input();
                equals();
            }
        }

    }

    public static void main(String[] args) {
        input();
        generate();
        equals();
        System.out.println("The number of attempts: " + i);
        System.out.println("The numbers are equal.");
    }
}