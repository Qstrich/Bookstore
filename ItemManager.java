
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ItemManager {
    private int maxItems; 
    private int currentItemNum;
    private Item[] item;

    public ItemManager(int maxItems) {
        items = new Item[maxItems];
        currentItemNum = 0;
    }
    public void sortItemAscendingPriceAlpha() {
        for (int i = 0; i <= currentItemNum; i++) {
            int max = i;
            for (int j = i + 1; j <= currentItemNum; j++) {
                if ((item[j].getPrice() == item[max].getPrice() && item[j].getName().compareTo(item[max].getName()) >= 0) || item[j].getPrice() < item[max].getPrice()) {
                    max = j;
                }
            }
        }
    }
    public void sortItemDescendingPriceAlpha() {

    }
    public void sortItemName() {

    }
    public void listItems() {
        for (int i = 0; i <= currentItemNum; i++) {
            System.out.println(i + 1 + " " + item[i]);
        }
    }
    public void listItemsBetweenPrice(double l, double r) {
        sortItemAscendingPriceAlpha();
        int lo = lower_bound(l), hi = upper_bound(r);
        for (int i = lo; i <= hi; i++) {
            System.out.println((i + 1) + " " + item[i]);
        }
    }
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
    public Item searchItem(int id) {
        int i = searchItemIdx(id);
        if (i == -1) return null;
        return item[i];
    }
    private int searchItemIdx(int id) {
        for (int i = 0; i <= currentItemNum; i++) {
            if (item[i].getId() == id) return i;
        }
        return -1;
    }
    public void deleteItem(Item i) {
        
    }
    public boolean addItem(int id, String author, String name, double price, int stock, String description, int type) {
        if (currentItemNum + 1 == maxItems) return false;
        if (type == 0) {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        } else {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        }
        return true;
    }
    public boolean loadFromFile(String fileName) {
        int type;
        String name;
        int id;
        double price;
        String description;
        int stock;
        String author;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            currentItemNum = Integer.parseInt(br.readLine());
            for (int i = 0; i <= currentItemNum; i++) {
                type = Integer.parseInt(br.readLine());
                name = br.readLine();
                id = Integer.parseInt(br.readLine());
                price = Double.parseDouble(br.readLine());
                description = br.readLine();
                stock = Integer.parseInt(br.readLine());
                author = br.readLine();
                addItem(id, author, name, price, stock, description, type);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public boolean saveToFile(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(currentItemNum +""); bw.newLine();
            for (int i = 0; i < currentItemNum; i++) {
                bw.write(item[i].getName() + ""); bw.newLine();
                bw.write(item[i] instanceof Book ? "0": "1"); bw.newLine();
                bw.write(item[i].getId()); bw.newLine();
                bw.write(item[i].getPrice() + ""); bw.newLine();
                bw.write(item[i].getDescription()); bw.newLine();
                bw.write(item[i].getStock() + ""); bw.newLine();
                bw.write(item[i] instanceof Book ? item[i].getAuthor() : item[i].getArtist()); bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}