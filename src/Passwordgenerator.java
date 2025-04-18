import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passwordgenerator {
    private boolean uppercase;
    private boolean numbers;
    private boolean symbols;
    private Integer length;
    private Scanner scanner;
    private List<Character> passwordList;
    private List<Character> lowercaseList;
    private List<Character> symbolList;
    public Passwordgenerator(){
        this.length = 0;
        this.uppercase = false;
        this.numbers = false;
        this.symbols = false;
        this.scanner = new Scanner(System.in);
        this.passwordList = new ArrayList<>();
        this.lowercaseList = new ArrayList<>();
        this.symbolList = new ArrayList<>();
    }

    private void input(){
        try {
            System.out.println("How long should your password be?");
            length = Integer.parseInt(scanner.nextLine());
            if (length < 1){
                System.out.println("Your password has to have a minimum of 1 letter");
                input();
            }
            System.out.println("Should your password have uppercased letters?");
            uppercase = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Should your password have numbers?");
            numbers = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Should your password have Symbols?");
            symbols = Boolean.parseBoolean(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("This is not a number, try entering a valid number");
            input();
        }
    }
    private void generator() {
        List<Integer> activeTypes = new ArrayList<>();
        if (uppercase) activeTypes.add(1); // Großbuchstaben
        if (numbers) activeTypes.add(2);  // Zahlen
        if (symbols) activeTypes.add(3);  // Symbole
        activeTypes.add(0); // Kleinbuchstaben (immer aktiv)

        for (int i = 0; i < length; i++) {
            int randomType = activeTypes.get((int) (Math.random() * activeTypes.size()));
            switch (randomType) {
                case 0: { // Kleinbuchstaben
                    int rnd = (int) (Math.random() * lowercaseList.size());
                    Character cha = lowercaseList.get(rnd);
                    passwordList.add(cha);
                    break;
                }
                case 1: { // Großbuchstaben
                    int rnd = (int) (Math.random() * lowercaseList.size());
                    Character cha = lowercaseList.get(rnd);
                    passwordList.add(Character.toUpperCase(cha));
                    break;
                }
                case 2: { // Zahlen
                    int rnd = (int) (Math.random() * 10);
                    passwordList.add((char) (rnd + '0'));
                    break;
                }
                case 3: { // Symbole
                    int rnd = (int) (Math.random() * symbolList.size());
                    Character cha = symbolList.get(rnd);
                    passwordList.add(cha);
                    break;
                }
            }
        }
    }

    private void listCreation(){
        for (char c = 'a'; c <= 'z'; c++) {
            lowercaseList.add(c);
        }
        char[] symbols = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', '\'', '"', '<', '>', ',', '.', '?', '/'};
        for (char symbol : symbols) {
            symbolList.add(symbol);
        }
    }

    private void start() {
        input();
        listCreation();
        generator();
        for (char c : passwordList) {
            System.out.print(c);
        }
    }
    public static void main(String[] args) {
        Passwordgenerator password = new Passwordgenerator();
        password.start();
    }
}
