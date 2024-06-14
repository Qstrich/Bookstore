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
    public static final String ACCOUNT_FILE = "account";
    public static final String ITEM_FILE = "item";
    public static final String ORDER_FILE = "order";
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

     /* 
     * boolean addCustomer(String name, String password) 
     * Return boolean - If the customer was added successfully. 
     * 
     * String name - name of Customer
     * String password - password of Customer
     * This method adds a Customer to the accountManager
     */
    public boolean addCustomer(String name, String password) {
        return accountList.addCustomer(name, password);
    }

 
    /* 
     * boolean addEmployee(String employeeKey, String name, String password) 
     * Return boolean - If the employee was added successfully.
     *  
     * String employeeKey - employeekey that the user enters
     * String name - name of Customer
     * String password - password of Customer
     * This method adds an Employee to the accountManager
     */
    public boolean addEmployee(String employeeKey, String name, String password) {
        return accountList.addEmployee(employeeKey, name, password);
    }
    /* listItemAscendingPriceAlpha()
     * This method lists the items in ascending order by first price, then name
     */
    public void listItemAscendingPriceAlpha() {
        itemList.sortItemAscendingPriceAlpha();
        itemList.listItems();
    }
    /* listItemDescendingPriceAlpha() 
     * This method lists the items in descneding order by first price, then ascending name
     */
    public void listItemDescendingPriceAlpha() {
        itemList.sortItemDescendingPriceAlpha();
        itemList.listItems();
    }
    /* listItemsBetweenPrice(double min, double max)
     * This method lists the items in between the min and max given
     */
    public void listItemsBetweenPrice(double min, double max) {
        itemList.listItemsBetweenPrice(min, max);
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
                    if (buyer != null) {
                        buyer.addToHistory(orders[i]);
                    }
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

                    // Write item id depending on if item was previously deleted
                    Item product = orders[i].getProduct();
                    if (product == null) {
                        writer.write("-1");   
                    }
                    else {
                        writer.write("" + product.getId());
                    }
                    writer.newLine();
                 
                    writer.write("" + orders[i].getQty());
                    writer.newLine();
                    writer.write("" + orders[i].getPrice());
                    writer.newLine();

                    // Write buyer name depending on if the account was previously deleted or not
                    Account Buyer = orders[i].getBuyer();
                    if (Buyer == null) {
                        writer.write("null");   
                    }
                    else {
                        writer.write(Buyer.getName());
                    }
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
    /* boolean login(String name, String password)
     * This method checks if the information matches and logs in. 
     */
    public boolean login(String name, String password) {
        Account temp = accountList.searchAccount(name);
        if(temp != null && temp.getPassword().equals(password)){
            currentUser = temp;
            return true;
        }
        currentUser = null;
        return false;
    }
    /* 
     * void logout()
     * Return void
     *  
     * This method logs out of the account by setting currentUser to null
     */
    public void logout() {
        currentUser = null;
    }
    /* 
     * void deleteCurrentAccount()
     * Return void
     *  
     * This method deletes the current account
     */
    public void deleteCurrentAccount() {
        accountList.deleteAccount(currentUser);
        logout();
    }
    /* 
     * void addToBalance(double amount) 
     * Return void
     *  
     * double amount - amount of money to add to the balance of the current account
     * This method adds money to the current account
     */
    public void addToBalance(double amount) {
        currentUser.setBalance(currentUser.getBalance() + amount);
    }
 
    /* 
     * void changePassword(String newPassword) 
     * Return void
     *  
     * String newPassword - New password to change
     * This method changes the current password of the current account
     */
    public void changePassword(String newPassword) {
        currentUser.setPassword(newPassword);
    }
    /* selectItem(int option)
     * 
     * This method selects the item sepcificed by idx
     */
    public void selectItem(int option) {
        selectedItem = itemList.getItems(option - 1);
    }

    /* listOrderHistory()
     *  This method lists the order history of the current user
     */
    public void listOrderHistory() {
        Order[] temp = currentUser.getOrderHistory();
        System.out.println("\nOrder History");
        for(int i = 0; i< currentUser.getCurrentOrderHistory(); i++){
            System.out.println(i+1);
            System.out.println(temp[i]);
        }

    }
    
    /*  openItemInHistory(int historyNum)
     *  This method opens the item in the history by idx
     */
    public void openItemInHistory(int historyNum) {
        currentUser.getOrderHistory(historyNum - 1).getProduct().useItem();
    }
    /* deleteItem()
     *  This method deletes the selected item
     */
    public void deleteItem() {
        itemList.deleteItem(selectedItem);
        selectedItem = null;
    }
    /*   changeItemPrice(double newPrice)
     *  This method changes the selected items price
     */
    public void changeItemPrice(double newPrice) {
        selectedItem.setPrice(newPrice);
    }
    /*   changeItemStock(int newStock)
     *  This method changes the selected items stock
     */
    public void changeItemStock(int newStock) {
        selectedItem.setStock(newStock);
    }

    /*
     * String toString()
     * Return String - Formatted string of fields. 
     * 
     * The method returns a string form of this object.
     */
    public String toString() {
        String format = "\nAccount manager: " + accountList + "\nItem manager: " + itemList + "\n\nOrders\nCurrent number of orders: " + currentOrderNum + "\nMaximum number of orders: " + MAX_ORDER;
        for (int i = 0; i < currentOrderNum; i++) {
            format += "\n" + orders[i] + "\n";
        }
        return format;
    }

}
