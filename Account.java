
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
