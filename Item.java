
public abstract class Item {
    private String name;
    private double price;
    private int stock;
    private String description;
    private int id; 
    
    public Item(String name, double price, int stock, String description, int id) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.id = id;
    }
    public abstract boolean useItem();
    public boolean enoughStock(int amt) {
        
    }


}