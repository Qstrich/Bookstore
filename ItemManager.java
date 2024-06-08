public class ItemManager {
    private int maxItems; 
    private int currentItemNum;
    private Item[] items;

    public ItemManager(int maxItems) {
        items = new Item[maxItems];
        currentItemNum = 0;
    }
    public void sortItemAscendingPriceAlpha() {
        
    }
    public void sortItemDescendingPriceAlpha() {

    }
    public void sortItemName() {

    }
    public void listItems() {

    }
    public void listItemsBetweenPrice(double l, double r) {

    }
    public Item searchItem(int id) {

    }
    public void deleteItem(Item i) {

    }
    public boolean addItem(int id, String name, double price, int stock, String description) {
        
    }
    public boolean loadFromFile(String fileName) {

    }
    public boolean saveToFile(String fileName) {

    }

}