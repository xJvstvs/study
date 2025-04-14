import java.util.Scanner;

public class testyourmath {

    private Scanner scanner;
    private Integer solution;
    private Integer userinput;
    private Integer streak;

    public testyourmath() {
        this.scanner = new Scanner(System.in);
        this.solution = null;
        this.userinput = null;
        this.streak = 0;
    }

    private void getInput() {
        System.out.println("Enter your Solution");
        try {
            userinput = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Number.");
            getInput();
        }
    }

    public void generateProblem() {
        Integer number1 = (int) (Math.random() * 10) + 1;
        Integer number2 = (int) (Math.random() * 10) + 1;
        System.out.println("Whats " + number1 + " * " + number2 + "?");
        solution = number1 * number2;
    }
    public void compare(){
        if (solution == userinput) {
            streak++;
            System.out.println("This is the correct answer, you only have to complete " + (10 - streak) + " to finish it");
            System.out.println(streak);
        } else {
            System.out.println("This is a wrong answer, your streak is back to 0");
            streak = 0;
            System.out.println(streak);
        }
    }

    public void start(){
        while (streak < 10) {
            generateProblem();
            getInput();
            compare();
        }
    }

    public static void main(String[] args) {
        testyourmath math = new testyourmath();
        math.start();
    }
}
