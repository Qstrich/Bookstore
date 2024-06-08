
public abstract class Account {
    private String name;
    private String password;
    private double balance;
    private int currentOrderHistory;
    public final int MAX_ORDER_HISTORY = 50;
    private Order[] orderHistory;

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

    
    public double compareToName(Account other){
        return name.compareTo(other.name);
    } 
    
    public void addToHistory(Order newOrder){
        if(currentOrderHistory < MAX_ORDER_HISTORY){
            orderHistory[currentOrderHistory] = newOrder;
            currentOrderHistory++;
        }
    }

    public abstract double getDiscount();

    public String toString(){
        return "Name: "+name+"\nPassword: "+password+"\nBalance: "+balance;
    }

}
