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
        sortItemAscendingPriceAlpha();
        int lo = lower_bound(l), hi = upper_bound(r);
        for (int i = lo; i <= hi; i++) {
            System.out.println((i + 1) + " " + items[i]);
        }
    }
    private int lower_bound(double price) {
        int hi = currentItemNum - 1, lo = 0, ans = currentItemNum;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (items[mid].getPrice() >= price) {
                ans = mid; hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        } 
        return ans;
    }
    private int upper_bound(double price) {
        int hi = currentItemNum - 1, lo = 0, ans = currentItemNum;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (items[mid].getPrice() > price) {
                ans = mid; hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
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