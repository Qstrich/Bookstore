/*
 * Bookstore.java
 * Brian, Jimmy, Jason
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains all methods, constants, and fields required
 * to run an online bookstore. 
 */

 import java.io.*;

public class Bookstore {
    public static final int MAX_ACCOUNT = 100;
    public static final int MAX_ITEM = 100;
    public static final int MAX_ORDER = 500;
    private int currentOrderNum;
    public static final String ACCOUNT_FILE = "account.txt";
    public static final String ITEM_FILE = "item.txt";
    public static final String ORDER_FILE = "order.txt";
    private Account currentUser;
    private Item selectedItem;
    private Order[] orders;
    private AccountManager accountList;
    private ItemManager itemList;

    /*
     * public Bookstore()
     * 
     * The constructor initialises all fields and sets up all arrays
     * using class constants. 
     */
    public Bookstore() {
        currentOrderNum = 0;
        orders = new Order[MAX_ORDER];
        accountList = new AccountManager(MAX_ACCOUNT);
        itemList = new ItemManager(MAX_ITEM);
    }

    // Accessors
    public int getCurrentOrderNum() {
        return currentOrderNum;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Order getOrders(int index) {
        return orders[index];
    }

    public AccountManager getAccountList() {
        return accountList;
    }

    public ItemManager getItemList() {
        return itemList;
    }

    // Mutators
    public void setCurrentOrderNum(int currentOrderNum) {
        this.currentOrderNum = currentOrderNum;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setOrders(int index, Order orders) {
        this.orders[index] = orders;
    }

    public void setAccountList(AccountManager accountList) {
        this.accountList = accountList;
    }

    public void setItemList(ItemManager itemList) {
        this.itemList = itemList;
    }

    
    public boolean addCustomer(String name, String password) {
        return accountList.addCustomer(name, password);
    }

    public boolean addEmployee(String employeeKey, String name, String password) {
        return accountList.addEmployee(employeeKey, name, password);
    }

    public void listItemAscendingPriceAlpha() {

    }

    public void listItemDescendingPriceAlpha() {

    }

    public void listItemsBetweenPrice(double min, double max) {

    }

    /* 
     * boolean placeOrder(int day, int month, int year, int qty)
     * Return boolean - If the order was placed successfully. 
     * 
     * int day - Current day when order was placed. 
     * int month - Current day when order was placed. 
     * int year - Current year when order was placed. 
     * int qty - Amount of items to buy. 
     * The method places an order for the logged in account on the selected item.
     * It only makes the order if there is enough stock and it changes the account
     * balance and item stock correspondingly while adding the order to the order
     * array and account's order history. 
     */
    public boolean placeOrder(int day, int month, int year, int qty) {
        if (selectedItem.enoughStock(qty)) {
            Order o = new Order(currentUser, selectedItem, currentOrderNum, day, month, year, qty);
            orders[currentOrderNum] = o;
            currentOrderNum++;
            currentUser.addToHistory(o);
            currentUser.setBalance(currentUser.getBalance() - (o.getPrice() * (1 + 0.01 * Order.TAX)));
            selectedItem.setStock(selectedItem.getStock() - qty);
            return true;
        }
        return false;
    }

    /*
     * void renewMembership()
     * 
     * The method renews the customer's membership and runs the special perk of the 
     * membership. It then changes the customer's balance. The method only works if 
     * the current account is of Customer type. 
     */
    public void renewMembership() {
        Membership renew = ((Customer)currentUser).getMembership();
        renew.setIssueYear(renew.getIssueYear() + 1);
        currentOrderNum += renew.specialPerk(currentOrderNum, itemList.getItems(), orders);
        currentUser.setBalance(currentUser.getBalance() - renew.getRenewalCost());
    }

    /*
     * void subscribeMembership(int membershipLevel, int issueDay, int issueMonth, int issueYear)
     * 
     * int membershipLevel - Option of membership: 1 BronzeMember, 2 SilverMember, 3 GoldMember.
     * int issueDay - The current day.
     * int issueMonth - The current month.
     * int issueYear - The current year.
     * The method adds the selected membership to the currentUser customer account. It
     * only works if the currentUser is an instance of Customer. 
     */
    public void subscribeMembership(int membershipLevel, int issueDay, int issueMonth, int issueYear) {
        Membership member = null;
        
        // Create membership
        switch (membershipLevel) {
            case 1:
                member = new BronzeMember(issueDay, issueMonth, issueYear, (Customer)currentUser);
                break;
            case 2:
                member = new SilverMember(issueDay, issueMonth, issueYear, (Customer)currentUser);
                break;
            case 3: 
                member = new GoldMember(issueDay, issueMonth, issueYear, (Customer)currentUser);
        }
        // Set the customer membership and run the special perk of the membership and change customer balance. 
        ((Customer)currentUser).setMembership(member);
        currentOrderNum += member.specialPerk(currentOrderNum, itemList.getItems(), orders);
        currentUser.setBalance(currentUser.getBalance() - member.getRenewalCost());

    }

    /*
     * boolean loadFromFile()
     * Return boolean - If load was successful
     * 
     * The method loads up the accountList, itemList, and orders array
     * using text files. 
     */
    public boolean loadFromFile() {
        if (accountList.loadFromFile(ACCOUNT_FILE) && itemList.loadFromFile(ITEM_FILE)) {
            // Load file for orders
            int id;
            int day;
            int month;
            int year;
            int itemId;
            int qty;
            double price;
            String accountName;
            Account buyer;
            Item product;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE));
                currentOrderNum = Integer.parseInt(reader.readLine());
                for (int i = 0; i < currentOrderNum; i++) {
                    // Read all information of the order from file
                    id = Integer.parseInt(reader.readLine());
                    day = Integer.parseInt(reader.readLine());
                    month = Integer.parseInt(reader.readLine());
                    year = Integer.parseInt(reader.readLine());
                    itemId = Integer.parseInt(reader.readLine());
                    qty = Integer.parseInt(reader.readLine());
                    price = Double.parseDouble(reader.readLine());
                    accountName = reader.readLine();

                    // Find the item and account associated with this order
                    buyer = accountList.searchAccount(accountName);
                    product = itemList.searchItem(itemId);

                    // Construct and add order into array
                    orders[i] = new Order(buyer, product, id, day, month, year, qty, price);
                    buyer.addToHistory(orders[i]);
                }
                reader.close();

                return true;
            }

            catch(IOException ioex) {
            }
        }
        return false;
    }

    /*
     * boolean saveToFile()
     * Return boolean - If the save was successful. 
     * 
     * The method saves all the information of this object into multiple files. 
     */
    public boolean saveToFile() {
        if (accountList.saveToFile(ACCOUNT_FILE) && itemList.saveToFile(ITEM_FILE)) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, false));
                writer.write("" + currentOrderNum);
                writer.newLine();

                // Save information of all orders to file
                for (int i = 0; i < currentOrderNum; i++) {
                    writer.write("" + orders[i].getId());
                    writer.newLine();
                    writer.write("" + orders[i].getDay());
                    writer.newLine();
                    writer.write("" + orders[i].getMonth());
                    writer.newLine();
                    writer.write("" + orders[i].getYear());
                    writer.newLine();
                    writer.write("" + orders[i].getProduct().getId());
                    writer.newLine();
                    writer.write("" + orders[i].getQty());
                    writer.newLine();
                    writer.write("" + orders[i].getPrice());
                    writer.newLine();
                    writer.write(orders[i].getBuyer().getName());
                    writer.newLine();
                }
                writer.close();

                return true;
            } 
            
            catch (IOException ioex) {
            }
        }
        return false;
    }

    public boolean login(String name, String password) {
        Account temp = accountList.searchAccount(name);
        if(temp != null && temp.getPassword().equals(password)){
            currentUser = temp;
            return true;
        }
        currentUser = null;
        return false;
    }

    public void logout() {

    }

    public void deleteCurrentAccount() {

    }

    public void addToBalance(double amount) {

    }

    public void changePassword(String newPassword) {

    }

    public void selectItem(int option) {

    }

    /*
     * 
     */
    public void listOrderHistory() {


    }
    
    /*
     * 
     */
    public void openItemInHistory(int historyNum) {

    }

    public boolean deleteItem() {

    }

    public void changeItemPrice(double newPrice) {

    }

    public void changeItemStock(int newStock) {

    }

}
