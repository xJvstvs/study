import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shoppinglist {

    private List<String> shoppinglist;
    private Scanner scanner;

    public Shoppinglist() {
        this.scanner = new Scanner(System.in);
        this.shoppinglist = new ArrayList<>();

    }

    public void addProduct(){
        System.out.println("What do you want to add");
        String item = scanner.nextLine();
        shoppinglist.add(item);
    }

    public void showList(){
        System.out.println("This is your shoppinglist");
        if(shoppinglist.size() > 0) {
            Integer i = 0;
            for (String c : shoppinglist) {
                System.out.print(c);
                i++;
                if(i < shoppinglist.size()) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("Your Shoppinglist is empty");
        }
    }

    public void removeItem(){
        System.out.println("What item do you want to remove?");
        String item = scanner.nextLine();
        if (shoppinglist.remove(item)) {
            System.out.println(item + " was removed.");
        } else {
            System.out.println("Item not found in the list.");
        }
    }

    public void start(){
        while (true) {
            System.out.println();
            System.out.println("What do you want to do? 1 = add Product, 2 = remove Product, 3 = Show List, 4 = Exit");
            try {
                int num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1: {
                        addProduct();
                        break;
                    }
                    case 2: {
                        removeItem();
                        break;
                    }
                    case 3: {
                        showList();
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
        Shoppinglist shop = new Shoppinglist();
        shop.start();
    }
}
