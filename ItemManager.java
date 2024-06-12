
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
        item = new Item[maxItems];
        currentItemNum = 0;
    }

    public Item[] getItems() {
        return item;
    }

    public Item getItems(int index) {
        return item[index];
    }

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
    public void sortItemDescendingPriceAlpha() {
        for (int i = 1; i < currentItemNum; i++) {
            int min = i;
            for (int j = i - 1; j >= 0; j--) {
                if ((item[j].getPrice() == item[min].getPrice() && item[j].getName().compareTo(item[min].getName()) >= 0) || item[j].getPrice() > item[min].getPrice()) {
                    item[j+1] =  item[j];
                }
            }
            item[i] = item[min]; 
        }
    }
    public void listItems() {
        for (int i = 0; i < currentItemNum; i++) {
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
        for (int i = 0; i < currentItemNum; i++) {
            if (item[i].getId() == id) return i;
        }
        return -1;
    }
    public void deleteItem(Item i) {
        int getIdx = searchItemIdx(i.getId());
        if (getIdx == -1) return;
        item[getIdx] = item[currentItemNum];
        item[currentItemNum--] = null;
    }
    public boolean addItem(int itemType, String name, double price, int stock, String description, int id, String author) {
        if (currentItemNum + 1 == maxItems) return false;
        if (itemType == 0) {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        } else {
            item[currentItemNum++] = new Book(author, name, price, stock, description, id);
        }
        return true;
    }
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
                bw.write(item[i] instanceof Book ? ((Book) item[i]).getAuthor() : item[i].getArtist()); bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}