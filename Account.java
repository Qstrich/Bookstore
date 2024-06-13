/*
 * Account.java
 * Jimmy Tao
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the fields and methods for the abstract class Account
 */
public abstract class Account {

    //Fields
    private String name;
    private String password;
    private double balance;
    private int currentOrderHistory;
    public final int MAX_ORDER_HISTORY = 50;
    private Order[] orderHistory;

    /*
     * Account(String name, String password)
     * 
     * String name - name of account
     * String password - password of account
     * The constructor initialises all fields and sets the array according to the max size.
     */

    public Account(String name, String password){
        this.name = name;
        this.password = password;
        orderHistory = new Order[MAX_ORDER_HISTORY];
    }

    // Accessors
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public int getCurrentOrderHistory() {
        return currentOrderHistory;
    }

    public Order[] getOrderHistory() {
        return orderHistory;
    }

    public Order getOrderHistory(int index) {
        return orderHistory[index];
    }

    // Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrentOrderHistory(int currentOrderHistory) {
        this.currentOrderHistory = currentOrderHistory;
    }

    public void setOrderHistory(Order[] orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setOrderHistory(int index, Order orderHistory) {
        this.orderHistory[index] = orderHistory;
    }

    
    /*
     * double compareToName()
     * Return double - Returns the comparison of this name with the other name. 
     * 
     * The method compares 2 accounts by their name. 
     */
    public double compareToName(Account other){
        return name.compareTo(other.name);
    } 

    /*
     * void addToHistory()
     * Return void   
     * 
     * Order newOrder - An order that was made by the account
     * The method takes in an Order and adds it to the order history 
     */
    public void addToHistory(Order newOrder){
        if(currentOrderHistory < MAX_ORDER_HISTORY){
            orderHistory[currentOrderHistory] = newOrder;
            currentOrderHistory++;
        }
    }

    /*
     * abstract double getDiscount()
     * Return double   
     * 
     * The method returns the discount for the account based on it's type
     */
    public abstract double getDiscount();

    /*
     * String toString()
     * Return String - Formatted string containing field information. 
     * 
     * The method returns the fields of the account in a formatted string. 
     */
    public String toString(){
        return "Name: "+name+"\nPassword: "+password+"\nBalance: "+balance;
    }

}
