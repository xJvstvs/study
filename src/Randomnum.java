import java.util.Scanner;

public class Randomnum {
    public Integer number;
    public Integer rndmnum;
    public Integer i;

    public Randomnum() {
        this.number = 0;
        this.rndmnum = 0;
        this.i = 0;
    }

    private void input() {
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

    private void generate() {
        i = 1;
        rndmnum = (int)(Math.random() * 100) + 1;
    }

    private void equals(){
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

    public void play(){
        input();
        generate();
        equals();
        System.out.println("The number of attempts: " + i);
        System.out.println("The numbers are equal.");
    }

    public static void main(String[] args) {
        Randomnum num = new Randomnum();
        num.play();
    }
}