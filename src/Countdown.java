import java.util.Scanner;

public class Countdown {
    public Integer secs;
    public Scanner scanner;


    private Countdown() {
        this.secs = 0;
        this.scanner = new Scanner(System.in);
    }

    private void input() {
        System.out.println("Enter the Seconds");
        try {
            secs = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Number.");
            input();
        }
    }

    private void counting(Integer input) throws InterruptedException {
        Integer i;
        for (i = 0; i < input; i++) {
            System.out.println(input - i);
            Thread.sleep(1000);
        }
        System.out.println("Zeit ist abgelaufen");
    }

    public void start() throws InterruptedException {
        input();
        counting(secs);
    }


    public static void main(String[] args) throws InterruptedException {
        Countdown Countdown = new Countdown();
        Countdown.start();
    }
}
