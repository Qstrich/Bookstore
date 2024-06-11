/*
 * BookstoreRunner.java
 * Brian, Jimmy, Jason
 * June 9, 2024
 * A.Y. Jackson S.S
 * 
 * The class runs the bookstore. 
 */

import java.util.*;

public class BookstoreRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int option;
        boolean exit = false;
        Bookstore store = new Bookstore();
        store.loadFromFile();

        // Loop menus until close program option is selected. 
        while (!exit) {
            // Login menu
            if (store.getCurrentUser() == null) {
                // Print menu options
                System.out.println("\n1. Create account");
                System.out.println("2. Login account");
                System.out.println("3. Close program");
                System.out.print("Enter the option number: ");
                option = enterInt();

                // Do options
                switch (option) {
                    case 1: 

                        break;
                    case 2:

                        break;
                    case 3:
                        exit = true;
                }
            }
            // Main store menu
            else {
                if (store.getSelectedItem() == null) {
                    System.out.println("\n1. Logout");
                    System.out.println("2. Delete account");
                    System.out.println("3. Print account information");
                    System.out.println("4. Change account password");
                    System.out.println("5. Add money to balance");
                    System.out.println("6. List items in ascending price then alphabetical order");
                    System.out.println("7. List items in descending order then alphabetical order");
                    System.out.println("8. List items ascending price then alphabetical order between two prices");
                    System.out.println("9. Select item by item number");
                    System.out.println("10. List order history");
                    System.out.println("11. Open order item with order history number");
                    System.out.println("12. Save bookstore information to file");

                    // Show special options only to Customer type accounts
                    if (store.getCurrentUser() instanceof Customer) {
                        System.out.println("13. Subscribe to annual membership");
                        System.out.println("14. Renew annual membership");
                    }
                    // Show special options only to Employee type accounts
                    else if (store.getCurrentUser() instanceof Employee) {
                        System.out.println("13. Add item to bookstore");
                    }
                    System.out.print("Enter the option number: ");
                    option = enterInt();

                    // Run options
                    switch (option) {
                        case 1:

                            break;
                        case 2: 

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        case 5:

                            break;
                        case 6:

                            break;
                        case 7:

                            break;
                        case 8:

                            break;
                        case 9:

                            break;
                        case 10:

                            break;
                        case 11:

                            break;
                        case 12:

                            break;
                        case 13:
                            // Subscribe to annual membership
                            if (store.getCurrentUser() instanceof Customer) {
                                int membershipLevel;
                                int issueDay, issueMonth, issueYear;
                                // Get inputs for membership information
                                System.out.print("\nEnter membership tier (1 for Bronze, 2 for Silver, 3 for Gold): ");
                                membershipLevel = enterInt();
                                System.out.print("Enter today's day: ");
                                issueDay = enterInt();
                                System.out.print("Enter today's month: ");
                                issueMonth = enterInt();
                                System.out.print("Enter today's year: ");
                                issueYear = enterInt();

                                // Create and connect membership
                                store.subscribeMembership(membershipLevel, issueDay, issueMonth, issueYear);
                            }
                            // Add item to bookstore
                            else if (store.getCurrentUser() instanceof Employee) {
                                
                            }
                            break;
                        case 14:
                            // Renew membership
                            if (store.getCurrentUser() instanceof Customer) {
                                // Renew membership or print error message. 
                                if (((Customer)store.getCurrentUser()).getMembership() != null) {
                                    store.renewMembership();
                                }
                                else {
                                    System.out.print("\nNo membership to renew. ");
                                }
                            }
                    }
                }
                // Item menu
                else {
                    System.out.println("\n1. Place order ons selected item");
                    System.out.println("2. Deselect item");

                    // Print options only available for Employee type accounts
                    if (store.getCurrentUser() instanceof Employee) {
                        System.out.println("3. Delete item");
                        System.out.println("4. Change item stock");
                        System.out.println("5. Change item price");
                    }

                    System.out.print("Enter option number: ");
                    option = enterInt();

                    // Run options
                    switch (option) {
                        case 1:
                            int qty;
                            int day, month, year;
                            System.out.print("\nEnter quantity to order: ");
                            qty = enterInt();
                            System.out.print("Enter today's day: ");
                            day = enterInt();
                            System.out.print("Enter today's month: ");
                            month = enterInt();
                            System.out.print("Enter today's year: ");
                            year = enterInt();

                            // Print message if order was successful or not
                            if (store.placeOrder(day, month, year, qty)) {
                                System.out.print("\nOrder placed successfully.");
                            }
                            else {
                                System.out.print("\nNot enough stock.");
                            }
                            break;
                        case 2:
                            store.setSelectedItem(null);
                            break;
                        case 3:
                            if (store.getCurrentUser() instanceof Employee) {

                            }
                            break;
                        case 4:
                            if (store.getCurrentUser() instanceof Employee) {

                            }
                            break;
                        case 5:
                            if (store.getCurrentUser() instanceof Employee) {

                            }
                    }
                }
            }
        }
    }

    /*
     * static int enterInt()
     * Return int - Integer entered by user. 
     * 
     * The method gets an integer input encompassed with a try catch block
     * to ensure the program does not crash. Using nextLine() of scanner
     * does not require to run a nextLine() to clear the scanner of the 
     * main when trying to get a String input after using this method. 
     */
    public static int enterInt() {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean complete = false;

        // Loop until proper input is entered
        while (!complete) {
            try {
                input = sc.nextInt();
                complete = true;
            }
            catch (InputMismatchException e) {
                System.out.println("\nEnter an integer.");
                sc.next();
            }
        }

        return input;
    }

    /*
     * static double enterDouble()
     * Return double - Double that was entered. 
     * 
     * The method gets a double input encompassed with a try catch block
     * to ensure the program does not crash. Using nextLine() of scanner
     * does not require to run a nextLine() to clear the scanner of the 
     * main when trying to get a String input after using this method. 
     */
    public static double enterDouble() {
        Scanner sc = new Scanner(System.in);
        double input = 0;
        boolean complete = false;

        // Loop until proper input is entered
        while (!complete) {
            try {
                input = sc.nextDouble();
                complete = true;
            }
            catch (InputMismatchException e) {
                System.out.println("\nEnter a double (integer or decimal).");
                sc.next();
            }
        }

        return input;
    }
}
