
public abstract class Item {
    //fields
    private String name;
    private double price;
    private int stock;
    private String description;
    private int id; 
    /*
     * public
     */
    public Item(String name, double price, int stock, String description, int id) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.id = id;
    }
    public String getName() { return name; }
    public double getPrice() {return price; }
    public int getStock() { return stock; }
    public String getDescription() { return description; }
    public int getId() {return id;}
    public void setName(String name) { this.name = name; }
    public void setD

    public abstract boolean useItem();
    public boolean enoughStock(int amt) {
        return amt <= stock;
    }
    public String toString() {
        return "Name: " + name + "\nPrice " + price + "\nStock " + stock + "\nDescription " + "\nID: " + id;
    }
}