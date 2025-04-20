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
            newCustomer.put("name", customerName);
            JSONObject customerName = new JSONObject();
            customerName.put(id + 1,newCustomer);
            customerList.add(customerName);
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
        System.out.println(lastID);
        return lastID;
    }


    private void start() throws IOException, ParseException {
        fileImplementation();
        id = getLastID();
        addCustomer();

    }


    public static void main(String[] args) throws IOException, ParseException {
        Receipt receipt = new Receipt();
        receipt.start();
    }
}
