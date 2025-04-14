import java.util.Scanner;

public class dices {
    private Integer CompTotal;
    private Integer PlayerTotal;
    private Integer RoundNr;
    private Integer TotalRounds;
    private Scanner Scanner;

    public dices(){
        this.CompTotal = 0;
        this.PlayerTotal = 0;
        this.RoundNr = 0;
        this.TotalRounds = 0;
        this.Scanner = new Scanner(System.in);
    }

    private void computerDicesGenerator() throws InterruptedException {
        Integer dice = (int) (Math.random() * 6) + 1;
        CompTotal = CompTotal + dice;
        System.out.println("The Computer has a " + dice + " in total he has " + CompTotal);
        Thread.sleep(2000);
    }

    private void playerDiceGenerator() throws InterruptedException {
        Integer dice = (int) (Math.random() * 6) + 1;
        PlayerTotal = PlayerTotal + dice;
        System.out.println("The Player has a " + dice + " in total he has " + PlayerTotal);
        Thread.sleep(2000);
    }
    private void calculation(){
        if(CompTotal == PlayerTotal){
            System.out.println("Its a Draw!");
        } else if(CompTotal > PlayerTotal){
            System.out.println("Computer Won!");
        } else {
            System.out.println("You Won!");
        }
    }
    private void input(){
        System.out.println("Enter the Amount of Rounds you want to play");
        try {
            TotalRounds = Integer.valueOf(Scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Number.");
            input();
        }
    }

    public void startgame() throws InterruptedException {
        input();
        while (RoundNr < TotalRounds) {
            computerDicesGenerator();
            playerDiceGenerator();
            RoundNr++;
        }
        calculation();
    }

    public static void main(String[] args) throws InterruptedException {
        dices dices = new dices();
        dices.startgame();
    }
}
