/*
 * Order.java
 * Brian Chen
 * June 6, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the information and methods for an order made in the bookstore. 
 */

 public class Order {
    private Account buyer;
    private Item product;
    private int id;
    private int day;
    private int month;
    private int year;
    private int qty;
    private double price;
    public static final double TAX = 13;

    /*
     * Order(Account buyer, Item product, int id, int day, int month, int year, int qty)
     * 
     * Account buyer - The account that made the order. 
     * Item product - Item that is ordered. 
     * int id - Order id corresponding to the index of the order in the order array.
     * int day - Day order was made.
     * int month - Month order was made. 
     * int year - Year order was made.
     * int qty - Quantity ordered.
     * The constructor initialises all fields and calculates the total price excluding price to initialise price. 
     */
    public Order(Account buyer, Item product, int id, int day, int month, int year, int qty) {
        this.buyer = buyer;
        this.product = product;
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.qty = qty;

        // Update the buyer membership status if it is a Customer account.
        if (buyer instanceof Customer) {
            ((Customer)buyer).getMembership().isExpired(day, month, year);
        }

        price = totalCost();
    }

    /*
     * Order(Account buyer, Item product, int id, int day, int month, int year, int qty, double price)
     * 
     * Account buyer - The account that made the order. 
     * Item product - Item that is ordered. 
     * int id - Order id corresponding to the index of the order in the order array.
     * int day - Day order was made.
     * int month - Month order was made. 
     * int year - Year order was made.
     * int qty - Quantity ordered.
     * double price - Price of order before tax. 
     * The constructor initialises all fields.
     */
    public Order(Account buyer, Item product, int id, int day, int month, int year, int qty, double price) {
        this.buyer = buyer;
        this.product = product;
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.qty = qty;
        this.price = price;
    }

    // Accessors
    public Account getBuyer() {
        return buyer;
    }

    public Item getProduct() {
        return product;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }
    
    // Mutators
    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public void setProduct(Item product) {
        this.product = product;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    /*
     * double totalCost()
     * Return double - Total calculated costs including any account discounts but excluding tax. 
     * 
     * The method calculates the total cost of the order before tax. 
     */
    public double totalCost() {
        return (product.getPrice() * qty)*(1 - 0.01 * buyer.getDiscount());
    }

    /*
     * String toString()
     * Return String - Formatted string containing field information. 
     * 
     * The method returns the fields of the order in a formatted string. 
     */
    public String toString() {
        return "Ordered by: " + buyer.getName() + "\nOrdered item: " + product.getName()
        + "\nOrder ID: " + id + "\nOrdered date (dd/mm/yyyy): " + day + "/" + month + "/" + year
        + "\nQuantity purchased: " + qty + "\nPrice before tax: " + price + "\nPrice after tax: " + (price * (1 + 0.01 * TAX)); 
    }
}
