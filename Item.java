
public abstract class Item {
    //fields
    private String name;
    private double price;
    private int stock;
    private String description;
    private int id; 
    /*
     * public Item(String name, double price, int stock, String descirption, int id)
     * This method is a constructor for the item class initializing all fields
     * 
     */
    public Item(String name, double price, int stock, String description, int id) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.id = id;
    }
    //All accesors and mutators
    public String getName() { return name; }
    public double getPrice() {return price; }
    public int getStock() { return stock; }
    public String getDescription() { return description; }
    public int getId() {return id;}
    public void setName(String name) { this.name = name; }
    public void setDouble(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setDescription(String description) { this.description = description; }
    public void setId(int id) { this.id = id; }

    public abstract boolean useItem();
    public boolean enoughStock(int amt) {
        return amt <= stock;
    }
    public String toString() {
        return "Name: " + name + "\nPrice " + price + "\nStock " + stock + "\nDescription " + "\nID: " + id;
    }
}