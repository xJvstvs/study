import java.util.Scanner;

public class Banking {
    private String accountnr;
    private float balance;
    private Scanner scanner;
    public Banking(){
        this.accountnr = "";
        this.balance = 0;
        this.scanner = new Scanner(System.in);
    }

    private void addBalance(){
        System.out.println("Your balance is " + balance);
        System.out.println("How much do you want to add?");
        try {
            float add = Float.parseFloat(scanner.nextLine());
            if (add > 0) {
                balance = balance + add;
                System.out.println("Youve added " + add + " your new balance is " + balance);
            } else {
                System.out.println("Please enter a number greater than 0");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    private void removeBalance(){
        System.out.println("Your balance is " + balance);
        System.out.println("How much do you want to withdraw?");
        try {
            float remove = Float.parseFloat(scanner.nextLine());
            if (remove > 0 && remove <= balance) {
                balance = balance - remove;
                System.out.println("Youve removed " + remove + " your new balance is " + balance);
            } else {
                System.out.println("Please enter a number greater than 0 & have enough balance");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }

    }
    private void showBalance(){
        System.out.println("Your accountnr is " + accountnr);
        System.out.println("Your balance is "+ balance);
    }
    private void start(){
        System.out.println("Please enter your accountnumber");
        accountnr = scanner.nextLine();
        System.out.println("Your accountnr is:" + accountnr);
        while (true) {
            System.out.println("What do you want to do? Press 1 for Pay in, Press 2 for withdrawal, Press 3 for your Balance, Press 4 to exit");
            try {
                int num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1: {
                        addBalance();
                        break;
                    }
                    case 2: {
                        removeBalance();
                        break;
                    }
                    case 3: {
                        showBalance();
                        break;
                    }
                    case 4: {
                        System.out.println("Exit, goodbye");
                        return;
                    }
                    default: {
                        System.out.println("This is not a valid number, please restart again");
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

    }

    public static void main(String[] args) {
        Banking bank = new Banking();
        bank.start();
    }
}
