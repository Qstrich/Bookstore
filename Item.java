
public abstract class Item {
    private String name;
    private double price;
    private int stock;
    private String s;
    private int id; 
    
    public Item(String name, double price, int stock, String s, int id) {

    }
    public abstract boolean useItem();
    public boolean enoughStock(int amt) {
        
    }


}