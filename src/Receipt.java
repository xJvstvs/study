import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;

public class Receipt {
    private FileReader reader;
    private File jsonfile;
    private String customerName;
    private String customerAdress;
    private String customerAccountnr;
    private Integer id;
    private Scanner scanner;
    public Receipt(){
        this.jsonfile = new File("");
        this.customerName = "";
        this.customerAdress = "";
        this.customerAccountnr = "";
        this.scanner = new Scanner(System.in);
    }
    private void fileImplementation() {
        jsonfile = new File("src/customerlist.json");
        try {
            if (!jsonfile.exists()) {
                System.out.println("File does not exist. Creating a new file...");
                if (jsonfile.createNewFile()) {
                    System.out.println("File created successfully.");
                    try (FileWriter writer = new FileWriter(jsonfile)) {
                        writer.write("[]");
                    }
                } else {
                    System.out.println("Failed to create the file.");
                    return;
                }
            }
            reader = new FileReader(jsonfile);
            System.out.println("FileReader initialized");
            JSONParser parser = new JSONParser();
            JSONArray customerList = (JSONArray) parser.parse(reader);
            System.out.println("Customer list: " + customerList.toJSONString());
        } catch (IOException e) {
            System.out.println("Error handling the file: " + e.getMessage());
            reader = null;
        } catch (Exception e) {
            System.out.println("Error while processing the file");
        }
    }
    private void addCustomer() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray customerList;
            if (jsonfile.length() == 0) {
                customerList = new JSONArray();
            } else {
                customerList = (JSONArray) parser.parse(new FileReader(jsonfile));
            }
            addCustomerInput();
            JSONObject newCustomer = new JSONObject();
            newCustomer.put("adress", customerAdress);
            newCustomer.put("accountnr", customerAccountnr);
            newCustomer.put("name", customerName.toLowerCase());
            JSONObject customerid = new JSONObject();
            customerid.put(id + 1,newCustomer);
            customerList.add(customerid);
            try (FileWriter writer = new FileWriter(jsonfile)) {
                writer.write(customerList.toJSONString());
                System.out.println("Customer added successfully:");
                System.out.println("Name: " + customerName);
                System.out.println("---> Adress: " + customerAdress);
                System.out.println("---> Accountnumber: " + customerAccountnr);
                System.out.println("---> ID: " + (id + 1));
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error while adding customer: " + e.getMessage());
        }
    }

    private void addCustomerInput(){
        System.out.println("Whats this customers name?");
        customerName = scanner.nextLine();
        System.out.println("Whats the customers adress?");
        customerAdress = scanner.nextLine();
        System.out.println("Whats the customers accountnumber?");
        customerAccountnr = scanner.nextLine();
    }


    private int getLastID() {
        int lastID = -1;
        try {
            JSONParser parser = new JSONParser();
            if (jsonfile.length() == 0) {
                System.out.println("The file is empty.");
                return lastID;
            }

            JSONArray customerList = (JSONArray) parser.parse(new FileReader(jsonfile));
            for (Object obj : customerList) {
                JSONObject customerEntry = (JSONObject) obj;
                for (Object key : customerEntry.keySet()) {
                    int currentID = Integer.parseInt(key.toString());
                    if (currentID > lastID) {
                        lastID = currentID;
                    }
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error while retrieving the last ID: " + e.getMessage());
        }
        return lastID;
    }


    private void getCustomer() throws IOException, ParseException {
        System.out.println("Whats the name of the Customer you want to get");
        String customernamelocal = scanner.nextLine().toLowerCase();
        JSONParser parser = new JSONParser();
        JSONArray customerList = (JSONArray) parser.parse(new FileReader(jsonfile));
        for (Object obj : customerList) {
            JSONObject customerEntry = (JSONObject) obj;
            for (Object key : customerEntry.keySet()) { // Zugriff auf die ID
                JSONObject customerData = (JSONObject) customerEntry.get(key); // Inneres JSONObject
                String customern = (String) customerData.get("name"); // Zugriff auf den Namen
                if (customern.equals(customernamelocal)) {
                    System.out.println("---------------------------------");
                    System.out.println("Name: " + customerData.get("name"));
                    System.out.println("---> Adress: " + customerData.get("adress"));
                    System.out.println("---> Accountnumber: " + customerData.get("accountnr"));
                    System.out.println("---> ID: " + (customerEntry.keySet()));
                    System.out.println("---------------------------------");
                }
            }
        }
    }

    private void removeCustomer() throws IOException, ParseException {
        System.out.println("Whats the name of the Customer you want to remove?");
        String customernamelocal = scanner.nextLine().toLowerCase();
        JSONParser parser = new JSONParser();
        JSONArray customerList = (JSONArray) parser.parse(new FileReader(jsonfile));
        boolean customerFound = false;
        for (int i = 0; i < customerList.size(); i++) {
            JSONObject customerEntry = (JSONObject) customerList.get(i);
            for (Object key : customerEntry.keySet()) { // Access the ID
                JSONObject customerData = (JSONObject) customerEntry.get(key); // Inner JSONObject
                String customern = (String) customerData.get("name"); // Access the name
                if (customern.equals(customernamelocal)) {
                    customerList.remove(i); // Remove the customer entry from the array
                    customerFound = true;
                    System.out.println("Customer removed successfully.");
                    break;
                }
            }
            if (customerFound) break; // Exit the loop once the customer is removed
        }
        try (FileWriter writer = new FileWriter(jsonfile)) {
            writer.write(customerList.toJSONString());
        }
    }

    private void showList() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray customerList = (JSONArray) parser.parse(new FileReader(jsonfile));
        for (Object obj : customerList) {
            JSONObject customerEntry = (JSONObject) obj;
            for (Object key : customerEntry.keySet()) { // Zugriff auf die ID
                JSONObject customerData = (JSONObject) customerEntry.get(key); // Inneres JSONObject
                System.out.println("---------------------------------");
                System.out.println("Name: " + customerData.get("name"));
                System.out.println("---> Adress: " + customerData.get("adress"));
                System.out.println("---> Accountnumber: " + customerData.get("accountnr"));
                System.out.println("---> ID: " + (customerEntry.keySet()));
            }
        }
    }

    private void start() throws IOException, ParseException {
        fileImplementation();
        while (true) {
            System.out.println();
            System.out.println("What do you want to do? 1 = add Customer, 2 = get Customer, 3 = remove Customer, 4 = Show list, 5 = Exit");
            try {
                int num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1: {
                        id = getLastID();
                        addCustomer();
                        break;
                    }
                    case 2: {
                        getCustomer();
                        break;
                    }
                    case 3: {
                        removeCustomer();
                        break;
                    }
                    case 4:{
                        showList();
                        break;
                    }
                    case 5: {
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

    public static void main(String[] args) throws IOException, ParseException {
        Receipt receipt = new Receipt();
        receipt.start();
    }
}
