
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * ItemManager.java
 * Jason Medeiros
 * June 12, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the fields and methods for managing items. 
 */
public class ItemManager {
    //fields
    private int maxItems; 
    private int currentItemNum;
    private Item[] item;
    /*
    * ItemManager(int maxItems)
    * Constructor for item manager
    */
    public ItemManager(int maxItems) {
        item = new Item[maxItems];
        this.maxItems = maxItems;
        currentItemNum = 0;
    }
    //Accesors
    public Item[] getItems() {
        return item;
    }
    public Item getItems(int index) {
        return item[index];
    }
    public int getCurrentItemNum() {
        return currentItemNum;
    }
    /*
    * sortItemAscendingPriceAlpha()
    * sorts the array using selection sort in the ascending order by price then alphabetic order
    */
    public void sortItemAscendingPriceAlpha() {
        for (int i = 0; i < currentItemNum; i++) {
            int min = i;
            for (int j = i + 1; j < currentItemNum; j++) {
                if ((item[j].getPrice() == item[min].getPrice() && item[j].getName().compareTo(item[min].getName()) <= 0) || item[j].getPrice() < item[min].getPrice()) {
                    min = j;
                }
                Item temp = item[i];
                item[i] = item[min];
                item[min] = temp;
            }
        }
    }
    /*
    * sortItemAscendingPriceAlpha()
    * sorts the array using insertion sort in the descending order by price then alphabetic order
    */
    public void sortItemDescendingPriceAlpha() {
        for (int i = 1; i < currentItemNum; i++) {
            Item temp = item[i];
            for (int j = i - 1; j >= 0; j--) {
                if ((item[j].getPrice() == temp.getPrice() && item[j].getName().compareTo(temp.getName()) >= 0) || item[j].getPrice() > temp.getPrice()) {
                    item[j+1] = item[j];
                }
            }
            item[i] = temp; 
        }
    }
    /* listItems()
     * This method lists the item list for the user
     */
    public void listItems() {
        for (int i = 0; i < currentItemNum; i++) {
            System.out.println(i + 1 + " " + item[i]);
        }
    }
    /* listItemsBetweenPrice(double l, double r)
     * This method sorts the array ascending and uses binary search to find the upper and lowerbound of price to display
     */
    public void listItemsBetweenPrice(double l, double r) {
        sortItemAscendingPriceAlpha();
        int lo = lower_bound(l), hi = upper_bound(r);
        if(lo == hi) { System.out.println("There are no items"); return;}
        for (int i = lo; i < hi; i++) {
            System.out.println((i + 1) + " " + item[i]);
        }
    }
    /* int lower_bound(double price)
     * returns - int (the first index)
     * This method returns the index of the lowerbound
     */
    private int lower_bound(double price) {
        int hi = currentItemNum - 1, lo = 0, ans = currentItemNum;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (item[mid].getPrice() >= price) {
                ans = mid; hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        } 
        return ans;
    }
    /*  int upper_bound(double price)
     *  double - price (the price to look for)
     *  return int - the upperbound of price
     *  This method returns the upperbound of price
     */
    private int upper_bound(double price) {
        int hi = currentItemNum - 1, lo = 0, ans = currentItemNum;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (item[mid].getPrice() > price) {
                ans = mid; hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
    /*  Item searchItem(int id) 
     *  This method searches for the item using id
     */
    public Item searchItem(int id) {
        int i = searchItemIdx(id);
        if (i == -1) return null;
        return item[i];
    }
    /* int searchItemIdx(int id) 
     * This method uses sequential search to find the item
     */
    private int searchItemIdx(int id) {
        for (int i = 0; i < currentItemNum; i++) {
            if (item[i].getId() == id) return i;
        }
        return -1;
    }
    /* deleteItem(Item i)
     * Item i - the item to delete
     * This method deletes the specified item
     */
    public void deleteItem(Item i) {
        int getIdx = searchItemIdx(i.getId());
        if (getIdx == -1) return;
        item[getIdx] = item[currentItemNum];
        item[currentItemNum--] = null;
    }
    /* boolean addItem(int itemType, String name, double price, int stock, String description, int id, String author)
     * int itemType - the type of item, book ro 
     * String name - the name of the item
     * double price - the price of the item
     * int stock - how much of the item is left
     * String description - the desscipriotn of the item
     * int id - the id of the item
     * String author - the maker of the item (author/artist)
     * returns boolean - if added the item was successful
     * This method creates an item intializing all feilds
     */
    public boolean addItem(int itemType, String name, double price, int stock, String description, int id, String author) {
        if (currentItemNum + 1 == maxItems || searchItemIdx(id) != -1) return false;
        if (itemType == 0) {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        } else {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        }
        return true;
    }
    /* boolean loadFromFile(String fileName)
     * return boolean - returns if its successful
     * This method fills in the item array using the file
     */
    public boolean loadFromFile(String fileName) {
        int itemType;
        String name;
        int id;
        double price;
        String description;
        int stock;
        String author;
        int numItem; 
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            numItem = Integer.parseInt(br.readLine());
            for (int i = 0; i < numItem; i++) {
                itemType = Integer.parseInt(br.readLine());
                name = br.readLine();
                id = Integer.parseInt(br.readLine());
                price = Double.parseDouble(br.readLine());
                description = br.readLine();
                stock = Integer.parseInt(br.readLine());
                author = br.readLine();
                addItem(itemType, name, price, stock, description, id, author);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    /*  boolean saveToFile(String fileName)
     *  
     */
    public boolean saveToFile(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(currentItemNum +""); bw.newLine();
            for (int i = 0; i < currentItemNum; i++) {
                bw.write(item[i] instanceof Book ? "0": "1"); bw.newLine();
                bw.write(item[i].getName() + ""); bw.newLine();
                bw.write(item[i].getId() + ""); bw.newLine();
                bw.write(item[i].getPrice() + ""); bw.newLine();
                bw.write(item[i].getDescription()); bw.newLine();
                bw.write(item[i].getStock() + ""); bw.newLine();
                bw.write(item[i].getMaker()); bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String toString() {
        String format = "\nCurrent number of items: " + currentItemNum + "\nMaximum number of items: " + maxItems;
        for (int i = 0; i < currentItemNum; i++) {
            format += "\n" + item[i] + "\n";
        }
        return format;
    }
}