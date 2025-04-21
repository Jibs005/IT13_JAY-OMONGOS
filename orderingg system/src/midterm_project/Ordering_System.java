
package midterm_project;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ordering_System {

    public static final int SHIFT = 1;
    
static String[] PorkDishes = {
    "Pork Adobo", "Grilled Liempo", "Lechon Kawali",
    "Pork Sisig", "Sweet and Sour Pork", "Pork BBQ Skewers (2 pcs)"
};

static double[] PorkDishesPrice = {
    90.00, 110.00, 115.00, 100.00, 95.00, 60.00
};

     
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        
        File inputFile = new File("C:\\midtermDATA\\UserAccount.txt");
String currentUsername = "";
        
       
        
        
        while (running) {
            
            System.out.println("||============================================||");
            System.out.println("||*********   WELCOME TO FOODIEDINE   ********||");
            System.out.println("||                                            ||");
            System.out.println("||             *  FoodieDine  *               ||");
            System.out.println("||      Where Flavor Meets Happiness!         ||");
            System.out.println("||                                            ||");
            System.out.println("||     - Tasty  |  Cozy  |  Affordable -      ||");
            System.out.println("||                                            ||");
            System.out.println("||============================================||");
            System.out.println("||         Please choose an option:           ||");
            System.out.println("||     1 Login              2 Register        ||");
            System.out.println("||============================================||");
            System.out.print("||   Input choose: ");
                int choice = -1;
           
            while (choice != 1 && choice != 2) {
                System.out.print("|| Input choose: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); 
                    if (choice != 1 && choice != 2) {
                        System.out.println("|| Invalid input. Please enter 1 or 2. ||");
                    }
                } else {
                    System.out.println("|| Invalid input. Please enter 1 or 2. ||");
                    scanner.nextLine(); 
                }
            }
            if (choice == 1) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.println("||============================================||");
                    System.out.println("||*********     LOGIN TO FOODIEDINE     ******||");
                    System.out.println("||                                            ||");
                    System.out.println("||            Please enter your details       ||");
                    System.out.println("||                                            ||");
                    System.out.println("||============================================||");
                    System.out.print("|| Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("|| Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.println("||============================================||");

                    if (isValidLogin(inputFile, username, password)) {
                          currentUsername = username;
                                  
                        System.out.println("||============================================||");
                        System.out.println("|| Login successful for " + username + "!");
                        System.out.println("||============================================||");
                        loggedIn = true;

                        boolean inMenu = true;
                        while (inMenu) {
                            System.out.println("||============================================||");
                            System.out.println("||     WELCOME TO FOODIEDINE MAIN MENU        ||");
                            System.out.println("||============================================||");
                            System.out.println("|| 1. Choose Food                             ||");
                            System.out.println("|| 2. View Order History                      ||");
                            System.out.println("|| 3. Logout                                  ||");
                            System.out.println("||============================================||");
                            System.out.print("|| Enter your choice: ");
                            int menuChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (menuChoice) {
                            case 1:
    ArrayList<String> orderedItems = new ArrayList<>();
    ArrayList<Integer> orderedQuantities = new ArrayList<>();
    ArrayList<Double> orderedPrices = new ArrayList<>();
    boolean orderAgain = true;
    char piso = '₱';

    while (orderAgain) {
        System.out.println("||==========  PORK DISHES MENU  ==========||");
        for (int i = 0; i < PorkDishes.length; i++) {
            System.out.printf("|| %d. %-30s %c%.2f  ||\n", (i + 1), PorkDishes[i], piso, PorkDishesPrice[i]);
        }
        System.out.println("|| 0. Back to Main Menu                     ||");
        System.out.println("||========================================||");

        if (!orderedItems.isEmpty()) {
            System.out.println("|| Previous Orders:                        ||");
            for (int i = 0; i < orderedItems.size(); i++) {
                System.out.printf("|| - %-25s x%d (%c%.2f)  ||\n",
                        orderedItems.get(i), orderedQuantities.get(i), piso, orderedPrices.get(i));
            }
            double subtotal = 0;
            for (double price : orderedPrices) {
                subtotal += price;
            }
            System.out.printf("|| Subtotal: %c%.2f                         ||\n", piso, subtotal);
            System.out.println("||========================================||");
        }

        int dishChoice = -1;
        while (dishChoice < 0 || dishChoice > PorkDishes.length) {
            System.out.print("|| Select the number of the dish to order: ");
            if (scanner.hasNextInt()) {
                dishChoice = scanner.nextInt();
                scanner.nextLine();
                if (dishChoice < 0 || dishChoice > PorkDishes.length) {
                    System.out.println("|| Invalid selection. Please try again.    ||");
                }
            } else {
                System.out.println("|| Invalid input. Please enter a number.   ||");
                scanner.nextLine(); 
            }
        }

        if (dishChoice == 0) {
            System.out.println("|| Returning to main menu...               ||");
            break;
        } else {
            String selectedDish = PorkDishes[dishChoice - 1];
            double selectedPrice = PorkDishesPrice[dishChoice - 1];

            int quantity = -1;
            while (quantity <= 0) {
                System.out.print("|| Enter quantity: ");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if (quantity <= 0) {
                        System.out.println("|| Invalid quantity. Please enter a positive number. ||");
                    }
                } else {
                    System.out.println("|| Invalid input. Please enter a number.  ||");
                    scanner.nextLine(); 
                }
            }

            double totalPrice = selectedPrice * quantity;

            orderedItems.add(selectedDish);
            orderedQuantities.add(quantity);
            orderedPrices.add(totalPrice);

            System.out.println("||========================================||");
            System.out.printf("|| You ordered: %-25s x%d  ||\n", selectedDish, quantity);
            System.out.printf("|| Total Price: %c%.2f                      ||\n", piso, totalPrice);
            System.out.println("|| Thank you for your order!               ||");
            System.out.println("||========================================||");

            String again = "";
            while (!(again.equalsIgnoreCase("y") || again.equalsIgnoreCase("yes") || again.equalsIgnoreCase("n") || again.equalsIgnoreCase("no"))) {
                System.out.print("|| Would you like to order another pork dish? (Y/N): ");
                again = scanner.nextLine().trim();
                if (!(again.equalsIgnoreCase("y") || again.equalsIgnoreCase("yes") || again.equalsIgnoreCase("n") || again.equalsIgnoreCase("no"))) {
                    System.out.println("|| Invalid input. Please enter Y or N.    ||");
                }
            }

            if (again.equalsIgnoreCase("n") || again.equalsIgnoreCase("no")) {
                orderAgain = false;

                double subtotal = 0;
                for (double price : orderedPrices) {
                    subtotal += price;
                }

                System.out.println("|| Proceeding to payment...               ||");
                System.out.println("|| Payment Method: CASH                   ||");

                double amountPaid = 0;
                boolean validPayment = false;

                while (!validPayment) {
                    System.out.printf("|| Your total is: %c%.2f                    ||\n", piso, subtotal);
                    System.out.print("|| Enter amount to pay: ");

                    if (scanner.hasNextDouble()) {
                        amountPaid = scanner.nextDouble();
                        scanner.nextLine(); 

                        if (amountPaid < subtotal) {
                            System.out.println("|| Insufficient amount. Please try again. ||");
                            System.out.print("|| If you'd like to cancel the order, enter 'C'. To try again, enter a valid amount: ");
                            String cancelOption = scanner.nextLine().trim().toLowerCase();

                            if (cancelOption.equals("c")) {
                                System.out.println("|| Payment cancelled. Returning to the main menu... ||");
                                orderAgain = false;
                                break;
                            }
                        } else {
                            validPayment = true;
                        }
                    } else {
                        System.out.println("|| Invalid input. Please enter a number.  ||");
                        scanner.nextLine(); 
                    }
                }

                if (validPayment) {
                    double change = amountPaid - subtotal;

                    StringBuilder receipt = new StringBuilder();
                    receipt.append("========== FOODIEDINE RECEIPT ==========\n");
                    receipt.append("Customer: ").append(currentUsername).append("\n");
                    receipt.append("Date: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
                    receipt.append("----------------------------------------\n");

                    for (int i = 0; i < orderedItems.size(); i++) {
                        receipt.append(String.format("%-25s x%d  %c%.2f\n",
                                orderedItems.get(i), orderedQuantities.get(i), piso, orderedPrices.get(i)));
                    }

                    receipt.append(String.format("\nSubtotal:         %c%.2f\n", piso, subtotal));
                    receipt.append(String.format("Amount Paid:      %c%.2f\n", piso, amountPaid));
                    receipt.append(String.format("Change:           %c%.2f\n", piso, change));
                    receipt.append("========================================\n");
                    receipt.append("Thank you for dining with FoodieDine!\n");

                    System.out.println("\n||============= RECEIPT ==================||");
                    System.out.println(receipt.toString());

                    try {
                        File receiptFile = new File("C:\\midtermDATA\\Specific_History\\User_Receipt_" + currentUsername + ".txt");
                        receiptFile.getParentFile().mkdirs(); 
                        FileWriter writer = new FileWriter(receiptFile, true); 
                        writer.write("=== Order on " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ===\n");
                        for (int i = 0; i < orderedItems.size(); i++) {
                            writer.write(orderedItems.get(i) + " x" + orderedQuantities.get(i) + " - ₱" + String.format("%.2f", orderedPrices.get(i)) + "\n");
                        }
                        writer.write("Total: ₱" + String.format("%.2f", subtotal) + "\n");
                        writer.write("--------------------------------------------------\n");
                        writer.close();

                        System.out.println("|| Receipt saved to C:\\midtermDATA\\User_Receipt_" + currentUsername + ".txt ||");
                    } catch (IOException e) {
                        System.out.println("|| Error saving receipt to file.         ||");
                    }

                    System.out.println("|| Returning to main menu...              ||\n");
                }
            }
        }
    }
    break;





                         case 2:
    System.out.println("||========= YOUR ORDER HISTORY ==========||");

    try {
        File historyFile = new File("C:\\midtermDATA\\Specific_History\\User_Receipt_" + currentUsername + ".txt");

        if (!historyFile.exists()) {
            System.out.println("|| No order history found for " + currentUsername + ". ||");
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(historyFile));
            String line;
            boolean hasOrders = false;

            while ((line = reader.readLine()) != null) {
                System.out.println("|| " + line);
                hasOrders = true;
            }
            reader.close();

            if (!hasOrders) {
                System.out.println("|| Your order history is empty. ||");
            }
        }
    } catch (IOException e) {
        System.out.println("|| Error reading order history. ||");
    }

    boolean validChoice = false;
    while (!validChoice) {
        System.out.print("|| Would you like to go back to main menu? (Y/N): ");
        String backChoice = scanner.nextLine().trim();

        if (backChoice.equalsIgnoreCase("y") || backChoice.equalsIgnoreCase("yes")) {
            validChoice = true; 
            inMenu = false; 
        } else if (backChoice.equalsIgnoreCase("n") || backChoice.equalsIgnoreCase("no")) {
            validChoice = false; 
        } else {
            System.out.println("|| Invalid input. Please enter Y or N. ||");
        }
    }
    break;
                                case 3:
                                    System.out.println("|| Logging out...                             ||");
                                    inMenu = false;
                                    break;
                                default:
                                    System.out.println("|| Invalid option. Please try again.          ||");
                                    break;
                            }
                        }

                    } else {
    System.out.println("|| Invalid username or password.             ||");
    System.out.println("|| 1. Try Again                              ||");
    System.out.println("|| 2. Back to Main Menu                      ||");
    System.out.print("|| Enter your choice: ");
    int retryChoice = scanner.nextInt();
    scanner.nextLine();

    if (retryChoice == 2) {
        break; 
    }
}

                }

            } else if (choice == 2) {
                boolean registered = false;
                while (!registered) {
                    System.out.println("||============================================||");
                    System.out.println("||*********   REGISTER TO FOODIEDINE   *******||");
                    System.out.println("||                                            ||");
                    System.out.println("||            Please enter your details       ||");
                    System.out.println("||                                            ||");
                    System.out.println("||============================================||");
                    System.out.print("|| Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("|| Enter your password: ");
                    String password = scanner.nextLine();

                    try {
                        String encryptedPassword = encryptCaesar(password, SHIFT);

                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = now.format(formatter);

                        FileWriter writer = new FileWriter(inputFile, true);
                        writer.write("--------------------------------------\n");
                        writer.write("            user account              \n");
                        writer.write("Username: " + username + "\n");
                        writer.write("Password: " + encryptedPassword + "\n");
                        writer.write("Registered at: " + formattedDateTime + "\n");
                        writer.close();

                        System.out.println("||============================================||");
                        System.out.println("|| Registration successful for " + username + "!  ");
                        System.out.println("|| Registered at: " + formattedDateTime);
                        System.out.println("||============================================||");

                        registered = true;
                        choice = 1;
                    } catch (IOException e) {
                        System.out.println("|| Error writing to file: " + e.getMessage());
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input. Please choose 1 or 2.");
            }
        }

        scanner.close();
    }

    public static String encryptCaesar(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else if (Character.isDigit(ch)) {
                result.append((char) ((ch - '0' + shift) % 10 + '0'));
            } else {
                result.append(ch); 
            }
        }

        return result.toString();
    }

    public static boolean isValidLogin(File file, String username, String password) {
        String encryptedInputPassword = encryptCaesar(password, SHIFT);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String fileUsername = null;
            String filePassword = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    fileUsername = line.substring(10).trim();
                } else if (line.startsWith("Password: ")) {
                    filePassword = line.substring(10).trim();
                    if (fileUsername != null && filePassword != null) {
                        if (fileUsername.equals(username) && filePassword.equals(encryptedInputPassword)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("|| Error reading file: " + e.getMessage());
        }
        return false;
    }
}

